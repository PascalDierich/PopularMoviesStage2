package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.pascaldierich.popularmoviesstage2.R;
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
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainActivityPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainFragmentPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.adapter.ImageAdapter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.fragments.MainFragment;

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



        getPopularMovies(); // TODO: ändern zu get InitialData -> read out preferences
//        getFavoriteMovies();
//        getTopRatedMovies();
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
    public void onDownloadFinish(PageMovies movies) {
        mView.hideProgress();
        if (movies == null || movies.getResults().size() == 0) {
            throw new IllegalArgumentException("Arguments can not be null");
        }
        ArrayList<DetailMovieObject> movieObjectArrayList = Converter.PageMovieToArrayListDetailMovieObject(movies);

        if (movieObjectArrayList == null) {
            Log.d(LOG_TAG, "onDownloadFinish: is Null... :(");
        }
        Log.d(LOG_TAG, "onDownloadFinish: DetailMovieObject.size() = "  + movieObjectArrayList.size());
        // TODO: mView.showMovies()

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
