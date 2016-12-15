package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainActivityPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;

/**
 * Created by pascaldierich on 08.12.16.
 */

public class MainActivityPresenterImpl extends AbstractPresenter implements MainActivityPresenter {
	private static final String LOG_TAG = MainActivityPresenterImpl.class.getSimpleName();

	private MainActivityPresenter.View mView;

	public static boolean sTwoPaneMode;

	private SharedPreferences mSharedPreferences;

	public MainActivityPresenterImpl(Executor executor,
									 MainThread mainThread,
									 Bundle savedInstanceState,
									 View view) {
		super(executor, mainThread, savedInstanceState);
		this.mView = view;

		this.mSharedPreferences = mView.getPreferences();

		setUiMode();
	}

	@Override
	public void setUiMode() {
		if (mView.getUiMode()) { // TwoPaneMode == true
			mView.setTwoPaneMode(true);
		} else { // TwoPaneMode == false
			mView.setTwoPaneMode(false);
		}
	}

	@Override
	public void resume() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void stop() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void onError(int code) {

	}

	@Override
	public boolean onMenuItemSelected(int id) {
		Log.d(LOG_TAG, "onMenuItemSelected: with id = " + id);
		switch (id) {
			case R.id.menu_popularity: {
				this.mSharedPreferences
						.edit()
						.putInt(mView.getApplicationContext().getString(R.string.preferences_initial_sort),
								R.integer.preferences_initial_sort_popularity)
						.apply();
				// TODO: 15.12.16 need Callback to MainFragment
				break;
			}
			case R.id.menu_rating: {
				this.mSharedPreferences
						.edit()
						.putInt(mView.getApplicationContext().getString(R.string.preferences_initial_sort),
								R.integer.preferences_initial_sort_rating)
						.apply();
				break;
			}
			case R.id.menu_favorites: {
				this.mSharedPreferences
						.edit()
						.putInt(mView.getApplicationContext().getString(R.string.preferences_initial_sort),
								R.integer.preferences_initial_sort_favorites)
						.apply();
				break;
			}
			default: {
				return false;
			}
		}
		mView.restart();
		return true;
	}
}
