package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

import android.os.Looper;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;
import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.DownloadMoviesInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.TestInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.DownloadPopularMoviesInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.DownloadTopRatedMoviesInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.TestInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.repository.MoviesRepository;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pascaldierich on 08.12.16.
 */

public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        DownloadMoviesInteractor.Callback,
        TestInteractor.Callback {
    private static final String LOG_TAG = MainPresenterImpl.class.getSimpleName();

    private MainPresenter.View mView;
    private MoviesRepository mMoviesRepository;

    public MainPresenterImpl(Executor executor,
                             MainThread mainThread,
                             View view,
                             MoviesRepository moviesRepository) {
        super(executor, mainThread);
        this.mView = view;
        this.mMoviesRepository = moviesRepository;

        /*
        for test reasons
         */
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.d(LOG_TAG, "constructor: is on Main Thread");
        } else {
            Log.d(LOG_TAG, "constructor: is NOT on Main Thread");
        }
        getPopularMovies();
        startTest();
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
    public void onDownloadFinish(ArrayList<Movie> movies) {
        if (movies == null || movies.size() == 0) {
            throw new IllegalArgumentException("Arguments can not be null");
        }
        Log.d(LOG_TAG, "onDownloadFinish with movieList != null");

        // TODO: mView.showMovies()

    }

    @Override
    public void getPopularMovies() {
        DownloadMoviesInteractor downloadInteractor = new DownloadPopularMoviesInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mMoviesRepository // --> MoviesRepositoryImplementation
        );
        Log.d(LOG_TAG, "downloadInteractor is going to execute");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.d(LOG_TAG, "getPopularMovies: MainThread");
        } else {
            Log.d(LOG_TAG, "getPopularMovies: NOT MainThread");
        }
        downloadInteractor.execute();
        Log.d(LOG_TAG, "getPopularMovies: ");
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

    @Override
    public void startTest() {
        TestInteractor testInteractor = new TestInteractorImpl(
                mExecutor,
                mMainThread,
                this
        );
        testInteractor.execute();
    }

    @Override
    public void onTestFinish(int i) {
        Log.d(LOG_TAG, "onTestFinish: test finished with i = " + i);
    }
}
