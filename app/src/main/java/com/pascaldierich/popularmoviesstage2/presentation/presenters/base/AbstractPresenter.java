package com.pascaldierich.popularmoviesstage2.presentation.presenters.base;

import android.os.Bundle;

import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public abstract class AbstractPresenter {

	protected Executor mExecutor;
	protected MainThread mMainThread;
	private Bundle mSavedInstanceState;

	protected AbstractPresenter(Executor executor, MainThread mainThread, Bundle savedInstanceState) {
		mExecutor = executor;
		mMainThread = mainThread;
		mSavedInstanceState = savedInstanceState;
	}
}
