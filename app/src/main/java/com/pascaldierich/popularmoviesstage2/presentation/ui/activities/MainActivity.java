package com.pascaldierich.popularmoviesstage2.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.MoviesRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.data.storage.FavoriteRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.domain.executor.impl.ThreadExecutor;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.impl.MainPresenterImpl;
import com.pascaldierich.popularmoviesstage2.threading.MainThreadImpl;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate: lakf");
        setContentView(R.layout.activity_main);

        initMainPresenter();
    }

    private void initMainPresenter() {
        mMainPresenter = new MainPresenterImpl(
                ThreadExecutor.getInstance(), // TODO inject dependencies
                MainThreadImpl.getInstance(),
                this,
                new MoviesRepositoryImpl(),
                new FavoriteRepositoryImpl(this)
        );
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
    public void showMovies() {

    }
}
