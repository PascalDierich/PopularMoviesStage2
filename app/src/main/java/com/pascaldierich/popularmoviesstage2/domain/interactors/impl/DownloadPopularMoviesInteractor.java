package com.pascaldierich.popularmoviesstage2.domain.interactors.impl;

import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.DownloadMovies;
import com.pascaldierich.popularmoviesstage2.domain.interactors.base.AbstractInteractor;
import com.pascaldierich.popularmoviesstage2.domain.repository.MoviesRepository;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class DownloadPopularMoviesInteractor extends AbstractInteractor implements DownloadMovies {

    private DownloadMovies.Callback mCallback;
    private MoviesRepository mRepository;

    public DownloadPopularMoviesInteractor(Executor threadExecutor, MainThread mainThread,
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
        mRepository.downloadPopularMovies();
    }
}
