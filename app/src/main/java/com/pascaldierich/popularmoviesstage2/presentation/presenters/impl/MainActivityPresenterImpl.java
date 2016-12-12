package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

import android.os.Bundle;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageMovies;
import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.DownloadMoviesInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.QueryFavoriteMoviesInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.DownloadPopularMoviesInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.DownloadTopRatedMoviesInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.QueryFavoriteInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.repository.FavoriteRepository;
import com.pascaldierich.popularmoviesstage2.domain.repository.MoviesRepository;
import com.pascaldierich.popularmoviesstage2.presentation.converters.Converter;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainActivityPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.fragments.DetailFragment;

import java.util.ArrayList;

/**
 * Created by pascaldierich on 08.12.16.
 */

public class MainActivityPresenterImpl extends AbstractPresenter implements MainActivityPresenter {
    private static final String LOG_TAG = MainActivityPresenterImpl.class.getSimpleName();

    private MainActivityPresenter.View mView;

    public static boolean sTwoPaneMode;

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
            sTwoPaneMode = true;
            mView.setTwoPaneMode(sTwoPaneMode);
        } else { // TwoPaneMode == false
            sTwoPaneMode = false;
            mView.setTwoPaneMode(sTwoPaneMode);
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
    public void onError(String message) {

    }

}
