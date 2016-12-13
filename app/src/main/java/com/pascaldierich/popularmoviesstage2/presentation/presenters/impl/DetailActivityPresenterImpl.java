package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

import android.os.Bundle;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageReviews;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageTrailers;
import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.DownloadInfoForMovieInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.SaveFavoriteMovieInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.DownloadInfoReviewsInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.DownloadInfoTrailersInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.SaveFavoriteMovieInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.repository.DetailInfoMoviesRepository;
import com.pascaldierich.popularmoviesstage2.domain.repository.SaveMovieRepository;
import com.pascaldierich.popularmoviesstage2.presentation.converters.Converter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.DetailActivityPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class DetailActivityPresenterImpl extends AbstractPresenter implements DetailActivityPresenter,
        DownloadInfoForMovieInteractor.Callback,
        SaveFavoriteMovieInteractor.Callback {
    private static final String LOG_TAG = DetailActivityPresenterImpl.class.getSimpleName();

    private DetailActivityPresenter.View mView;
    private DetailInfoMoviesRepository mDetailRepository;
    private SaveMovieRepository mSaveRepository;

    public DetailActivityPresenterImpl(Executor executor,
                                       MainThread mainThread,
                                       Bundle savedInstanceState,
                                       View view,
                                       DetailInfoMoviesRepository detailRepository,
                                       SaveMovieRepository saveRepository) {
        super(executor, mainThread, savedInstanceState);
        this.mView = view;
        this.mDetailRepository = detailRepository;
        this.mSaveRepository = saveRepository;

        /*
        only for test reasons
         */
        getReviews(550); // TODO: get Id from ImageAdapter -> do this after UI testable
        getTrailer(550);
    }


    @Override
    public void getTrailer(int id) {
        DownloadInfoForMovieInteractor interactor = new DownloadInfoTrailersInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mDetailRepository,
                id
        );
        interactor.execute();
    }

    @Override
    public void getReviews(int id) {
        DownloadInfoForMovieInteractor interactor = new DownloadInfoReviewsInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mDetailRepository,
                id // TODO: get id
        );
        interactor.execute();
    }

    @Override
    public void saveAsFavorite() {
        SaveFavoriteMovieInteractor interactor = new SaveFavoriteMovieInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mSaveRepository,
                Converter.DetailMovieObjectToDataMovieObject(null)   // --> TODO: null = DetailMovieObject
        );
        interactor.execute();
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
    public void onDownloadTrailerFinish(PageTrailers page) {
        Log.d(LOG_TAG, "onDownloadTrailerFinish: Got It!");
        // TODO: mView.showTrailer()
    }

    @Override
    public void onDownloadReviewFinish(PageReviews page) {
        Log.d(LOG_TAG, "onDownloadReviewFinish: Got It!");
        // TODO: mView.showRevies()
    }

    @Override
    public void onSaveFinish(boolean success) {
        if (success) {
            Log.d(LOG_TAG, "onSaveFinish: Got It!");
        } else {
            Log.d(LOG_TAG, "onSaveFinish: Bad Luck :(");
        }
    }
}
