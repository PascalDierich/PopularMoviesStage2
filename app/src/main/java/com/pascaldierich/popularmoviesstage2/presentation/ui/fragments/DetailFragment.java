package com.pascaldierich.popularmoviesstage2.presentation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.DetailRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.data.storage.SaveMovieRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.domain.executor.impl.ThreadExecutor;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.DetailPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.impl.DetailPresenterImpl;
import com.pascaldierich.popularmoviesstage2.presentation.ui.BaseView;
import com.pascaldierich.popularmoviesstage2.threading.MainThreadImpl;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class DetailFragment extends Fragment implements BaseView,
		DetailPresenter.View {
	private static final String LOG_TAG = DetailFragment.class.getSimpleName();

	private DetailPresenter mPresenter;

	// View Components
	private TextView mTextViewTitle;
	private TextView mTextViewRelease;
	private TextView mTextViewLength;
	private TextView mTextViewRating;
	private TextView mTextViewDescription;

	private View mRootView;

	private ImageButton mImageButtonFavorite;

	private ImageView mImageViewThumbnail;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(false);

		initPresenter(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceBundle) {
		View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
		rootView = initViews(rootView);
		return rootView;
	}


	@Override
	public void showProgress() {

	}

	@Override
	public void hideProgress() {

	}

	@Override
	public void showError(String code) {
		Log.d(LOG_TAG, "showError: ERROR Code: " + code);
	}

	@Override
	public Context getApplicationContext() {
		return getContext();
	}

	@Override
	public void initPresenter(Bundle savedInstanceState) {
		mPresenter = new DetailPresenterImpl(
				ThreadExecutor.getInstance(),
				MainThreadImpl.getInstance(),
				savedInstanceState,
				this,
				new DetailRepositoryImpl(),
				new SaveMovieRepositoryImpl(getApplicationContext())
		);
	}

	public View initViews(View rootView) {
		Log.d(LOG_TAG, "initViews: YEAH!");
		this.mTextViewTitle = (TextView) rootView.findViewById(R.id.textView_title);
		this.mTextViewRelease = (TextView) rootView.findViewById(R.id.textView_release);
		this.mTextViewLength = (TextView) rootView.findViewById(R.id.textView_length);
		this.mTextViewRating = (TextView) rootView.findViewById(R.id.textView_rating);
		this.mTextViewDescription = (TextView) rootView.findViewById(R.id.textView_description);
		this.mImageButtonFavorite = (ImageButton) rootView.findViewById(R.id.imageButton_favorite);
		this.mImageViewThumbnail = (ImageView) rootView.findViewById(R.id.imageView_thumbnail);

		this.mRootView = rootView;
		return rootView;
	}

	@Override
	public int getSelectedMovieId() {
		return 3; // TODO: 15.12.16 get Movie Id
	}

	@Override
	public void showGivenData(DetailMovieObject movie) {
		Log.d(LOG_TAG, "showGivenData: called with movie != null -> " + (movie != null));
		this.mTextViewLength.setText("hallo");
		try {
			this.mTextViewTitle.setText(movie.getmTitle());
		} catch (NullPointerException npe) {
			Log.d(LOG_TAG, "showGivenData: NullPointerException when reading out title");
		}
		try {
			this.mTextViewRelease.setText(movie.getmRelease());
		} catch (NullPointerException npe) {
			Log.d(LOG_TAG, "showGivenData: NullPointerException when reading out release");
		}
		try {
			this.mTextViewRating.setText(movie.getmRating() + "/10"); // TODO: 14.12.16 save in strings.xml
		} catch (NullPointerException npe) {
			Log.d(LOG_TAG, "showGivenData: NullPointerException when reading out rating");
		}
		try {
			this.mTextViewDescription.setText(movie.getmDescription());
		} catch (NullPointerException npe) {
			Log.d(LOG_TAG, "showGivenData: NullPointerException when reading out description");
		}
	}
}
