package com.pascaldierich.popularmoviesstage2.presentation.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.pascaldierich.popularmoviesstage2.presentation.ui.BaseView;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class DetailFragment extends Fragment implements BaseView {
    private static final String LOG_TAG = DetailFragment.class.getSimpleName();

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
    public void initPresenter(Bundle savedInstaceState) {

    }
}
