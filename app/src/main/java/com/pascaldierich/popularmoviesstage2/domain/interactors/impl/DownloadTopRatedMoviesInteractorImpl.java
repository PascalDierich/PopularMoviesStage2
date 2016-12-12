package com.pascaldierich.popularmoviesstage2.domain.interactors.impl;

import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;
import com.pascaldierich.popularmoviesstage2.data.network.model.Page;
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

public class DownloadTopRatedMoviesInteractorImpl extends AbstractInteractor implements DownloadMoviesInteractor {

    private DownloadMoviesInteractor.Callback mCallback;
    private MoviesRepository mRepository;

    public DownloadTopRatedMoviesInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                                Callback callback, MoviesRepository repository) {
        super(threadExecutor, mainThread);

        if (repository == null || callback == null) {
            throw new IllegalArgumentException("Arguments can not be null");
        }

        this.mCallback = callback;
        this.mRepository = repository;
    }

    @Override
    public void run() {
        final Page movieList = mRepository.downloadTopRatedMovies();

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onDownloadFinish(movieList);
            }
        });
    }
}
