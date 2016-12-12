package com.pascaldierich.popularmoviesstage2.presentation.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.pascaldierich.popularmoviesstage2.data.network.MoviesRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageMovies;
import com.pascaldierich.popularmoviesstage2.data.storage.FavoriteRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.domain.executor.impl.ThreadExecutor;
import com.pascaldierich.popularmoviesstage2.domain.repository.FavoriteRepository;
import com.pascaldierich.popularmoviesstage2.domain.repository.MoviesRepository;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainFragmentPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.impl.MainFragmentPresenterImpl;
import com.pascaldierich.popularmoviesstage2.threading.MainThreadImpl;

import java.util.ArrayList;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class MainFragment extends Fragment implements MainFragmentPresenter.View {
    private static final String LOG_TAG = MainFragment.class.getSimpleName();

    private boolean mTwoPaneMode;

    private MainFragmentPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

        initPresenter(savedInstanceState);
    }

    @Override
    public void showProgress() {
        
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void initPresenter(Bundle savedInstanceState) {
        mPresenter = new MainFragmentPresenterImpl(
                ThreadExecutor.getInstance(), // TODO inject dependencies
                MainThreadImpl.getInstance(),
                savedInstanceState,
                this,
                new MoviesRepositoryImpl(),
                new FavoriteRepositoryImpl(getContext())
        );
    }

    public void setUseGridLayout(boolean twoPaneMode) {
        this.mTwoPaneMode = twoPaneMode;

    }

    @Override
    public void showMovies() {

    }
}
