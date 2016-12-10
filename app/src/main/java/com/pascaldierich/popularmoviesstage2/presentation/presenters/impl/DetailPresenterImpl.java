package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.DownloadInfoForMovieInteractor;
import com.pascaldierich.popularmoviesstage2.domain.repository.FavoriteRepository;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.DetailPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class DetailPresenterImpl extends AbstractPresenter implements DetailPresenter,
        DownloadInfoForMovieInteractor.Callback {

    private View mView;

    public DetailPresenterImpl(Executor executor,
                               MainThread mainThread,
                               View view
                                ) {
        super(executor, mainThread);
        this.mView = view;
    }


    @Override
    public void getTrailer() {

    }

    @Override
    public void getReviews() {

    }

    @Override
    public void saveAsFavorite() { // TODO: write ContentProvider

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
    public void onDownloadTrailerFinish() {
        // TODO: mView.showTrailer()
    }

    @Override
    public void onDownloadReviewFinish() {
        // TODO: mView.showRevies()
    }
}
