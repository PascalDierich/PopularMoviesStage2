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
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.DetailFragmentPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;
import com.pascaldierich.popularmoviesstage2.utils.ConstantsHolder;
import com.pascaldierich.popularmoviesstage2.utils.ErrorCodes;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class DetailFragmentPresenterImpl extends AbstractPresenter implements DetailFragmentPresenter,
        DownloadInfoForMovieInteractor.Callback,
        SaveFavoriteMovieInteractor.Callback {
    private static final String LOG_TAG = DetailFragmentPresenter.class.getSimpleName();

    private DetailFragmentPresenter.View mView;
    private DetailInfoMoviesRepository mDetailRepository;
    private SaveMovieRepository mSaveRepository;

    private int mMovieInternId;
    private DetailMovieObject mDetailMovieObject;

    public DetailFragmentPresenterImpl(Executor executor,
                                       MainThread mainThread,
                                       Bundle savedInstanceState,
                                       DetailFragmentPresenter.View view,
                                       DetailInfoMoviesRepository detailInfoRepo,
                                       SaveMovieRepository saveRepo) {
        super(executor, mainThread, savedInstanceState);

        this.mView = view;
        this.mDetailRepository = detailInfoRepo;
        this.mSaveRepository = saveRepo;

        checkSelectedMovie();
    }

    @Override
    public void checkSelectedMovie() {
        this.mMovieInternId = mView.getSelectedMovieId();
        Log.d(LOG_TAG, "checkSelectedMovie: intern Movie Id = " + mMovieInternId);
        if (this.mMovieInternId == ErrorCodes.internCommunication.NO_SELECTED_MOVIE) {
            onError(ErrorCodes.internCommunication.NO_SELECTED_MOVIE);
            return;
        }
        getDetailMovieObject();
    }

    @Override
    public void getDetailMovieObject() {
        this.mDetailMovieObject = ConstantsHolder.getDownloadedDataFromPosition(this.mMovieInternId);
        int _id;
        showGivenData();
        try {
            _id = mDetailMovieObject.getmId();
        } catch (Exception e) {
            onError(ErrorCodes.internCommunication.NOT_ENOUGH_INFO);
            Log.e(LOG_TAG, "getDetailMovieObject: couldnt get MovieID" + "\n" +
                    " --> " + e.fillInStackTrace());
            return;
        }
        getTrailer(_id);
        getReviews(_id);
    }

    @Override
    public void showGivenData() {
        mView.showGivenData(this.mDetailMovieObject);
    }

    @Override
    public void getTrailer(int id) {
        if (id == ErrorCodes.internCommunication.NOT_ENOUGH_INFO) {
            onError(ErrorCodes.internCommunication.NOT_ENOUGH_INFO);
        }
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
        if (id == ErrorCodes.internCommunication.NOT_ENOUGH_INFO) {
            onError(ErrorCodes.internCommunication.NOT_ENOUGH_INFO);
        }
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
        Log.d(LOG_TAG, "onError: error code = " + code);
        // TODO: 15.12.16 switch case ErrorCode.xxx
    }

    @Override
    public void onDownloadTrailerFinish(PageTrailers pageTrailers) {
        Log.d(LOG_TAG, "onDownloadTrailerFinish: Got It!");
    }

    @Override
    public void onDownloadReviewFinish(PageReviews pageReviews) {
        Log.d(LOG_TAG, "onDownloadReviewFinish: Got It");
    }

    @Override
    public void onSaveFinish(boolean success) {
        if (success) {
            Log.d(LOG_TAG, "onSaveFinish: Got It");
        } else {
            onError(ErrorCodes.dataStorage.SAVE_FAILED);
        }

    }
}
