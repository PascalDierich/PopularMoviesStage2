package com.pascaldierich.popularmoviesstage2.presentation.presenters.base;

import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;

/**
 * Created by pascaldierich on 08.12.16.
 */

public abstract class AbstractPresenter {

    protected Executor   mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }
}
