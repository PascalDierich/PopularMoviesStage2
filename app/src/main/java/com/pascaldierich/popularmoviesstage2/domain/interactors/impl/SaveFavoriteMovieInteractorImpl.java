package com.pascaldierich.popularmoviesstage2.domain.interactors.impl;

import android.graphics.Bitmap;

import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.base.AbstractInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.SaveFavoriteMovieInteractor;
import com.pascaldierich.popularmoviesstage2.domain.repository.SaveMovieRepository;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class SaveFavoriteMovieInteractorImpl extends AbstractInteractor implements SaveFavoriteMovieInteractor {

    SaveFavoriteMovieInteractor.Callback mCallback;
    SaveMovieRepository mRepository;

    String[] mDetailInfo;
    Bitmap mBitmap;

    public SaveFavoriteMovieInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                           SaveFavoriteMovieInteractor.Callback callback,
                                           SaveMovieRepository repository,
                                           String[] detailInfo,
                                           Bitmap bitmap) {
        super(threadExecutor, mainThread);

        if (repository == null || callback == null) {
            throw new IllegalArgumentException("Arguments can not be null");
        }

        this.mCallback = callback;
        this.mRepository = repository;
        this.mDetailInfo = detailInfo;
        this.mBitmap = bitmap;
    }

    @Override
    public void run() {
        final boolean success = mRepository.saveAsFavorite(mDetailInfo, mBitmap);

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onSaveFinish(success);
            }
        });
    }
}
