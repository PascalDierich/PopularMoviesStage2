package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.network.model.PageReviews;
import com.pascaldierich.popularmoviesstage2.data.network.model.PageTrailers;
import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.DownloadInfoForMovieInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.DownloadInfoReviewsInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.repository.DetailInfoMoviesRepository;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.DetailPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class DetailPresenterImpl extends AbstractPresenter implements DetailPresenter,
        DownloadInfoForMovieInteractor.Callback {
    private static final String LOG_TAG = DetailPresenterImpl.class.getSimpleName();

    private DetailPresenter.View mView;
    private DetailInfoMoviesRepository mRepository;

    public DetailPresenterImpl(Executor executor,
                               MainThread mainThread,
                               View view,
                               DetailInfoMoviesRepository repository
                                ) {
        super(executor, mainThread);
        this.mView = view;
        this.mRepository = repository;

        getReviews();
        getTrailer();
    }


    @Override
    public void getTrailer() {

    }

    @Override
    public void getReviews() {
        DownloadInfoForMovieInteractor interactor = new DownloadInfoReviewsInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mRepository,
                -1 // TODO: get id
        );
        interactor.execute();
    }

    @Override
    public void saveAsFavorite() {

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

    @Override
    public void onDownloadTrailerFinish(PageTrailers page) {
        Log.d(LOG_TAG, "onDownloadTrailerFinish: Got It");
        // TODO: mView.showTrailer()
    }

    @Override
    public void onDownloadReviewFinish(PageReviews page) {
        Log.d(LOG_TAG, "onDownloadReviewFinish: Got it");
        // TODO: mView.showRevies()
    }
}
