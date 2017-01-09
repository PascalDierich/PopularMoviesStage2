package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

import android.os.Bundle;

import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainActivityPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public class MainActivityPresenterImpl extends AbstractPresenter implements MainActivityPresenter {
	private static final String LOG_TAG = MainActivityPresenterImpl.class.getSimpleName();

	private MainActivityPresenter.View mView;

	public MainActivityPresenterImpl(Executor executor,
									 MainThread mainThread,
									 Bundle savedInstanceState,
									 View view) {
		super(executor, mainThread, savedInstanceState);
		this.mView = view;

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
}
