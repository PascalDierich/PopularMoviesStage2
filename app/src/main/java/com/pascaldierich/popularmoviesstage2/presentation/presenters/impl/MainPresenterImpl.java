package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageMovies;
import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.DownloadMoviesInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.QueryFavoriteMoviesInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.DownloadPopularMoviesInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.DownloadTopRatedMoviesInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.QueryFavoriteInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.repository.FavoriteRepository;
import com.pascaldierich.popularmoviesstage2.domain.repository.MoviesRepository;
import com.pascaldierich.popularmoviesstage2.presentation.converters.Converter;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;

import java.util.ArrayList;

/**
 * Created by pascaldierich on 08.12.16.
 */

public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        DownloadMoviesInteractor.Callback,
        QueryFavoriteMoviesInteractor.Callback {
    private static final String LOG_TAG = MainPresenterImpl.class.getSimpleName();

    private MainPresenter.View mView;
    private MoviesRepository mMoviesRepository;
    private FavoriteRepository mFavoriteRepository;

    public MainPresenterImpl(Executor executor,
                             MainThread mainThread,
                             View view,
                             MoviesRepository moviesRepository,
                             FavoriteRepository favoriteRepository) {
        super(executor, mainThread);
        this.mView = view;
        this.mMoviesRepository = moviesRepository;
        this.mFavoriteRepository = favoriteRepository;

        getPopularMovies(); // TODO: ändern zu get InitialData -> read out preferences
        getFavoriteMovies();
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
    public void onDownloadFinish(PageMovies movies) { // TODO: convert
        if (movies == null || movies.getResults().size() == 0) {
            throw new IllegalArgumentException("Arguments can not be null");
        }
        ArrayList<DetailMovieObject> movieObjectArrayList = Converter.PageMovieToArrayListDetailMovieObject(movies);

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
        QueryFavoriteMoviesInteractor queryInteractor = new QueryFavoriteInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mFavoriteRepository
        );
        Log.d(LOG_TAG, "getFavoriteMovies: going to execute queryInteractor");
        queryInteractor.execute();
    }

    @Override
    public void onQueryFinished(ArrayList<String[]> faveMovies) { // TODO: convert
        Log.d(LOG_TAG, "onQueryFinished: GOT IT!");
    }
}
