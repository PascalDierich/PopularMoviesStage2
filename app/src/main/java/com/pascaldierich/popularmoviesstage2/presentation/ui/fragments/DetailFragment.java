package com.pascaldierich.popularmoviesstage2.presentation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.pascaldierich.popularmoviesstage2.data.network.DetailRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.data.storage.SaveMovieRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.domain.executor.impl.ThreadExecutor;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.DetailPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.impl.DetailPresenterImpl;
import com.pascaldierich.popularmoviesstage2.presentation.ui.BaseView;
import com.pascaldierich.popularmoviesstage2.threading.MainThreadImpl;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class DetailFragment extends Fragment implements BaseView,
        DetailPresenter.View {
    private static final String LOG_TAG = DetailFragment.class.getSimpleName();
    
    private DetailPresenter mPresenter;

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
    public Context getApplicationContext() {
        return getContext();
    }

    @Override
    public void initPresenter(Bundle savedInstanceState) {
        mPresenter = new DetailPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                savedInstanceState,
                this,
                new DetailRepositoryImpl(),
                new SaveMovieRepositoryImpl(getApplicationContext())
        );
    }

    public void initView() {

    }

    @Override
    public int getSelectedMovieId() {
        return 0;
    }

    @Override
    public void showGivenData(DetailMovieObject movie) {
        // TODO: 15.12.16 get Key 
//        return getIntent().getIntExtra(getString(R.string.intent_string_key), ErrorCodes.internCommunication.NO_SELECTED_MOVIE);
    }
}
