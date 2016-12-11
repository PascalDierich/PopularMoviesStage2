package com.pascaldierich.popularmoviesstage2.domain.interactors.impl;

import android.os.Looper;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;
import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.DownloadMoviesInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.base.AbstractInteractor;
import com.pascaldierich.popularmoviesstage2.domain.repository.MoviesRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class DownloadPopularMoviesInteractorImpl extends AbstractInteractor implements DownloadMoviesInteractor {
    private static final String LOG_TAG = DownloadPopularMoviesInteractorImpl.class.getSimpleName();

    private DownloadMoviesInteractor.Callback mCallback;
    private MoviesRepository mRepository;

    public DownloadPopularMoviesInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                               Callback callback, MoviesRepository repository) {
        super(threadExecutor, mainThread);

        Log.d(LOG_TAG, "is in constructor");

        if (repository == null || callback == null) {
            throw new IllegalArgumentException("Arguments can not be null");
        }

        this.mCallback = callback;
        this.mRepository = repository;
    }

    @Override
    public void run() {
        Log.d(LOG_TAG, "run: should start now download");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.d(LOG_TAG, "run: Looper on Main");
        } else {
            Log.d(LOG_TAG, "run: Looper NOT on Main");
        }

        final ArrayList<Movie> movieList = mRepository.downloadPopularMovies();
        Log.d(LOG_TAG, "movieList got downloaded");

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG, "run: runs");
                mCallback.onDownloadFinish(movieList);
            }
        });
    }
}
