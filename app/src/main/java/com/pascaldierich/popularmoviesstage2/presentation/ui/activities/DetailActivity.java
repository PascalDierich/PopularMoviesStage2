package com.pascaldierich.popularmoviesstage2.presentation.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.DetailActivityPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.BaseView;
import com.pascaldierich.popularmoviesstage2.utils.ErrorCodes;

public class DetailActivity extends AppCompatActivity implements BaseView, DetailActivityPresenter.View {
    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Log.d(LOG_TAG, "onCreate: Im here bitches");

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

    }

    @Override
    public int getSelectedMovieId() {
        return getIntent().getIntExtra("", ErrorCodes.internCommunication.NO_SELECTED_MOVIE);
        // TODO: 14.12.16 save String key in strings.xml;
    }
}
