package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

import android.os.Bundle;

import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.DetailFragmentPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class DetailFragmentPresenterImpl extends AbstractPresenter implements DetailFragmentPresenter {


    public DetailFragmentPresenterImpl(Executor executor, MainThread mainThread, Bundle savedInstanceState) {
        super(executor, mainThread, savedInstanceState);
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
