package com.pascaldierich.popularmoviesstage2.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.domain.executor.impl.ThreadExecutor;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainActivityPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.impl.MainActivityPresenterImpl;
import com.pascaldierich.popularmoviesstage2.presentation.ui.callback.MovieSelectedCallback;
import com.pascaldierich.popularmoviesstage2.presentation.ui.fragments.DetailFragment;
import com.pascaldierich.popularmoviesstage2.presentation.ui.fragments.MainFragment;
import com.pascaldierich.popularmoviesstage2.threading.MainThreadImpl;
import com.pascaldierich.popularmoviesstage2.utils.ConstantsHolder;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View,
		MovieSelectedCallback {
	private static final String LOG_TAG = MainActivity.class.getSimpleName();

	private static final String DETAILFRAGMENT_TAG = "DetailFragmentTag";
	private static final String MAINFRAGMENT_TAG = "MainFragmentTag";

	private MainActivityPresenter mMainPresenter;

	private boolean mTwoPaneMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ConstantsHolder.setApiKey(getString(R.string.api_key));
		Log.d(LOG_TAG, "onCreate: api_key = " + getString(R.string.api_key));

		initPresenter(savedInstanceState, null);
	}

	@Override
	public void setTwoPaneMode(boolean twoPaneMode) {
		this.mTwoPaneMode = twoPaneMode;
		ConstantsHolder.setTwoPaneMode(twoPaneMode);
		if (twoPaneMode) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.movie_detail_container, new DetailFragment(), DETAILFRAGMENT_TAG)
					.commit();
		} else {
			getSupportActionBar().setElevation(0f);
		}

		if (getSupportFragmentManager().findFragmentByTag(MAINFRAGMENT_TAG) instanceof MainFragment) {
			// TODO: 03.01.17 restart fragment
			/*
			get the SavedInstanceState from MainFragment so you can destroy and create it from here with right state as parameter
			 */
		} else {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fragment_main_holder, new MainFragment(), MAINFRAGMENT_TAG)
					.commit();
		}

		

//		MainFragment mainFragment = ((MainFragment) getSupportFragmentManager()
//				.findFragmentById(R.id.fragment_main));
//		mainFragment.setUseGridLayout(this.mTwoPaneMode);
	}

	@Override
	public void initPresenter(Bundle savedInstanceState, @Nullable DetailMovieObject movieObject) {
		mMainPresenter = new MainActivityPresenterImpl(
				ThreadExecutor.getInstance(),
				MainThreadImpl.getInstance(),
				savedInstanceState,
				this
		);
	}

	@Override
	public Context getApplicationContext() {
		return this;
	}

	@Override
	public void showProgress() {
		// not used
	}

	@Override
	public void hideProgress() {
		// not used
	}

	@Override
	public void showError(String message) {
		// not used
	}

	@Override
	public boolean getUiMode() {
		return findViewById(R.id.movie_detail_container) != null;
	}

	@Override
	public void onMovieSelected(Bundle selectedMovie) {
		if (getUiMode()) { // TwoPaneMode
			DetailFragment fragment = new DetailFragment();
			fragment.setArguments(selectedMovie);

			getSupportFragmentManager().beginTransaction()
					.replace(R.id.movie_detail_container, fragment, DETAILFRAGMENT_TAG)
					.commit();
		} else {
			Intent intent = new Intent(this, DetailActivity.class);
			intent.putExtra(getString(R.string.extra_intent_key), selectedMovie);

			startActivity(intent);
		}
	}
}
