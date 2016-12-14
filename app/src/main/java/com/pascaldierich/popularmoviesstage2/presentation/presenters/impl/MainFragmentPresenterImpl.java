package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;

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
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainFragmentPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.adapter.ImageAdapter;
import com.pascaldierich.popularmoviesstage2.utils.ErrorCodes;
import com.pascaldierich.popularmoviesstage2.utils.Utility;

import java.util.ArrayList;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class MainFragmentPresenterImpl extends AbstractPresenter implements MainFragmentPresenter,
        DownloadMoviesInteractor.Callback,
        QueryFavoriteMoviesInteractor.Callback {
    private static final String LOG_TAG = MainFragmentPresenterImpl.class.getSimpleName();

    private MainFragmentPresenter.View mView;
    private MoviesRepository mMoviesRepository;
    private FavoriteRepository mFavoriteRepository;

    private boolean mTwoPaneMode;

    private ImageAdapter mImageAdapter;

    private ListView mListView;
    private GridView mGridView;

    public MainFragmentPresenterImpl(Executor executor,
                                     MainThread mainThread,
                                     Bundle savedInstanceState,
                                     MainFragmentPresenter.View view,
                                     MoviesRepository moviesRepository,
                                     FavoriteRepository favoriteRepository) {
        super(executor, mainThread, savedInstanceState);
        this.mView = view;
        this.mMoviesRepository = moviesRepository;
        this.mFavoriteRepository = favoriteRepository;


        getInitialData();
//        getFavoriteMovies();
//        getTopRatedMovies();
    }

    private void getInitialData() {
        if (!Utility.checkConnection((ConnectivityManager) mView.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE))) {
            onError(ErrorCodes.Network.NO_INTERNET);
            return;
        }

        // TODO: 14.12.16 get SharedPrefences
        getPopularMovies();
        /*
        or getTopRatedMovies()
        or getFavoriteMovies
         */
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
        switch (code) {
            case ErrorCodes.Network.DOWNLOAD_NULL: {
                Log.d(LOG_TAG, "onError: DOWNLOAD_NULL");
                // TODO: tell User about Problem
                // TODO: and check for Connection
            }
            case ErrorCodes.Network.NO_INTERNET: {
                Log.d(LOG_TAG, "onError: NO_INTERNET");
            }
        }
        // TODO: 14.12.16 checkConnection at the end
    }

    @Override
    public void onDownloadFinish(PageMovies movies) {
        mView.hideProgress();

        if (movies == null || movies.getResults().size() == 0) {
            onError(ErrorCodes.Network.DOWNLOAD_NULL);
            return;
        }

        ArrayList<DetailMovieObject> movieObjectArrayList = Converter.PageMovieToArrayListDetailMovieObject(movies);

        Log.d(LOG_TAG, "onDownloadFinish: DetailMovieObject.size() = "  + movieObjectArrayList.size());

        mView.showMovies(Converter.ArrayListWithDetailMovieObjectToArrayListWithGridItem(movieObjectArrayList));
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
        queryInteractor.execute();
    }

    @Override
    public void onQueryFinished(ArrayList<String[]> faveMovies) { // TODO: convert
        Log.d(LOG_TAG, "onQueryFinished: GOT IT!");
    }
}
