package com.pascaldierich.popularmoviesstage2.presentation.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.MoviesRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.data.storage.FavoriteRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.domain.executor.impl.ThreadExecutor;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainFragmentPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.impl.MainFragmentPresenterImpl;
import com.pascaldierich.popularmoviesstage2.presentation.ui.activities.DetailActivity;
import com.pascaldierich.popularmoviesstage2.presentation.ui.adapter.ImageAdapter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.model.GridItem;
import com.pascaldierich.popularmoviesstage2.threading.MainThreadImpl;

import java.util.ArrayList;


/**
 * Created by pascaldierich on 12.12.16.
 */

public class MainFragment extends Fragment implements MainFragmentPresenter.View {
	private static final String LOG_TAG = MainFragment.class.getSimpleName();

	private boolean mTwoPaneMode;

	private MainFragmentPresenter mPresenter;

	private ProgressBar mProgressBar;

	private ImageAdapter mImageAdapter;

	private GridView mGridView;

	private View mRootView;

	private Bundle mByNowSavedStates;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		Log.d(LOG_TAG, "onCreate: called");
		initPresenter(savedInstanceState, null);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		Log.d(LOG_TAG, "onCreateView: called");
		mRootView = inflater.inflate(R.layout.fragment_main, container, false);
		mProgressBar = (ProgressBar) mRootView.findViewById(R.id.progress_bar);

		return mRootView;
	}

	@Override
	public void showProgress() {
		mProgressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideProgress() {
		mProgressBar.setVisibility(View.INVISIBLE);
	}

	@Override
	public void showError(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
	}

	@Override
	public Context getApplicationContext() {
		return getContext();
	}

	@Override
	public void initPresenter(Bundle savedInstanceState, @Nullable DetailMovieObject movieObject) {
		mPresenter = new MainFragmentPresenterImpl(
				ThreadExecutor.getInstance(),
				MainThreadImpl.getInstance(),
				savedInstanceState,
				this,
				new MoviesRepositoryImpl(),
				new FavoriteRepositoryImpl(getContext())
		);
	}

	public void setUseGridLayout(boolean twoPaneMode) {
		this.mTwoPaneMode = twoPaneMode;
	}

	@Override
	public void showMovies(ArrayList<GridItem> movies) {
		if (mRootView == null) {
			Log.d(LOG_TAG, "showMovies: ROOTVIEW == NULL");
		}
		mGridView = (GridView) mRootView.findViewById(R.id.grid_view_main_fragment);
		if (this.mTwoPaneMode) {
			mImageAdapter = new ImageAdapter(getActivity(), R.layout.list_view_layout, movies);
			Log.d(LOG_TAG, "showMovies: selected list_view_layout");
		} else {
			mGridView.setNumColumns(GridView.AUTO_FIT);
			mImageAdapter = new ImageAdapter(getActivity(), R.layout.grid_view_layout, movies);
			Log.d(LOG_TAG, "showMovies: selected grid_view_layout");
		}

		mGridView.setAdapter(mImageAdapter);

		mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
				mPresenter.movieSelected(position);
			}
		});
	}

	@Override
	public void startDetailActivity(int position) {
		Intent intent = new Intent(getActivity(), DetailActivity.class)
				.putExtra(getString(R.string.intent_string_key), position);
		startActivity(intent);
	}

	@Override
	public boolean getTwoPaneMode() {
		return mTwoPaneMode;
	}

	@Override
	public int getInitialPreferences() {
		return getActivity()
				.getSharedPreferences(getString(R.string.preferences_initial_sort), Context.MODE_PRIVATE) // TODO: 15.12.16 ???
				.getInt(getString(R.string.preferences_initial_sort), R.integer.preferences_initial_sort_popularity);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		mPresenter.onMenuItemSelected(id);

		return true;
	}

	@Override
	public SharedPreferences getPreferences() {
		return getApplicationContext().getSharedPreferences("", Context.MODE_PRIVATE); // TODO: 15.12.16 String s
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main, menu);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		mByNowSavedStates = outState;
		final String KEY_DOWNLOADED_DATA = getString(R.string.main_fragment_downloaded_data);
		final String KEY_SELECTED_MOVIE = getString(R.string.main_fragment_selected_movie);

		Bundle presenterBundle = mPresenter.getState();

		// Check for downloaded Data
		try {
			outState.putParcelableArrayList(
					KEY_DOWNLOADED_DATA,
					presenterBundle.getParcelableArrayList(KEY_DOWNLOADED_DATA));
		} catch (NullPointerException e) {
			Log.e(LOG_TAG, "onSaveInstanceState: NullPointetException" + "\n" +
					" --> " + e.fillInStackTrace());
		}

		// Check for selected Movie
		try {
			outState.putParcelable(
					KEY_SELECTED_MOVIE,
					presenterBundle.getBundle(KEY_SELECTED_MOVIE)
			);
		} catch (NullPointerException e) {
			Log.e(LOG_TAG, "onSaveInstanceState: NullPointerException" + "\n" +
					" --> " + e.fillInStackTrace());
		}
	}

	@Override
	public Bundle restoreState() {
		return mByNowSavedStates;
	}

	@Override
	public void onResume() {
		super.onResume();
		mPresenter.resume();
	}
	
	@Override
	public void onStart() {
		super.onStart(); // TODO: 03.01.17 save mRootView in State (!!!!)
	}
}
