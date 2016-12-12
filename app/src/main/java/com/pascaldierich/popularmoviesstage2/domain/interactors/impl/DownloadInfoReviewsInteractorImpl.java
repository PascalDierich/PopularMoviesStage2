package com.pascaldierich.popularmoviesstage2.domain.interactors.impl;

import com.pascaldierich.popularmoviesstage2.data.network.model.PageReviews;
import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.DownloadInfoForMovieInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.base.AbstractInteractor;
import com.pascaldierich.popularmoviesstage2.domain.repository.DetailInfoMoviesRepository;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class DownloadInfoReviewsInteractorImpl extends AbstractInteractor implements DownloadInfoForMovieInteractor {

    private DownloadInfoForMovieInteractor.Callback mCallback;
    private DetailInfoMoviesRepository mRepository;
    private int mId;

    public DownloadInfoReviewsInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                             DownloadInfoForMovieInteractor.Callback callback,
                                             DetailInfoMoviesRepository repository,
                                             int id) {
        super(threadExecutor, mainThread);

        if (repository == null || callback == null) {
            throw new IllegalArgumentException("Arguments can not be null");
        }

        this.mId = id;
        this.mCallback = callback;
        this.mRepository = repository;
    }

    @Override
    public void run() {
        final PageReviews page = mRepository.downloadReviews(mId);

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onDownloadReviewFinish(page);
            }
        });
    }


}
