package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;
import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.DownloadMoviesInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.DownloadPopularMoviesInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.DownloadTopRatedMoviesInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.repository.MoviesRepository;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;

import java.util.List;

/**
 * Created by pascaldierich on 08.12.16.
 */

public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        DownloadMoviesInteractor.Callback {

    private MainPresenter.View mView;
    private MoviesRepository mMoviesRepository;

    public MainPresenterImpl(Executor executor,
                             MainThread mainThread,
                             View view,
                             MoviesRepository moviesRepository) {
        super(executor, mainThread);
        this.mView = view;
        this.mMoviesRepository = moviesRepository;
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
    public void onDownloadFinish(List<Movie> movies) {
        if (movies == null || movies.size() == 0) {
            throw new IllegalArgumentException("Arguments can not be null");
        }

        // TODO: mView.showMovies()

    }

    @Override
    public void getPopularMovies() {
        DownloadMoviesInteractor downloadInteractor = new DownloadPopularMoviesInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mMoviesRepository
        );
        downloadInteractor.execute();
    }

    @Override
    public void getTopRatedMovies() {
        DownloadMoviesInteractor downloadInteractor = new DownloadTopRatedMoviesInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mMoviesRepository
        );
        downloadInteractor.execute();
    }

    @Override
    public void getFavoriteMovies() {

    }
}
