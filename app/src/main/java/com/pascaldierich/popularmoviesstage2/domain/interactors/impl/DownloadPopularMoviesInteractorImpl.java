package com.pascaldierich.popularmoviesstage2.domain.interactors.impl;

import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageMovies;
import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.DownloadMoviesInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.base.AbstractInteractor;
import com.pascaldierich.popularmoviesstage2.domain.repository.MoviesRepository;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class DownloadPopularMoviesInteractorImpl extends AbstractInteractor implements DownloadMoviesInteractor {

    private DownloadMoviesInteractor.Callback mCallback;
    private MoviesRepository mRepository;

    public DownloadPopularMoviesInteractorImpl(Executor threadExecutor, MainThread mainThread,
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
        final PageMovies MOVIE_LIST = mRepository.downloadPopularMovies();

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onDownloadFinish(MOVIE_LIST);
            }
        });
    }
}
