package com.pascaldierich.popularmoviesstage2.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.MoviesRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.data.storage.FavoriteRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.domain.executor.impl.ThreadExecutor;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainActivityPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.impl.MainActivityPresenterImpl;
import com.pascaldierich.popularmoviesstage2.presentation.ui.fragments.DetailFragment;
import com.pascaldierich.popularmoviesstage2.presentation.ui.fragments.MainFragment;
import com.pascaldierich.popularmoviesstage2.threading.MainThreadImpl;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String DETAILFRAGMENT_TAG = "DetailFragmentTag";

    private MainActivityPresenter mMainPresenter;

    private boolean mTwoPaneMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPresenter(savedInstanceState);
    }

    @Override
    public void setTwoPaneMode(boolean twoPaneMode) {
        if (twoPaneMode) {
            Log.d(LOG_TAG, "setTwoPaneMode: true");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, new DetailFragment(), DETAILFRAGMENT_TAG)
                    .commit();
        } else {
            getSupportActionBar().setElevation(0f);
        }

        MainFragment mainFragment = ((MainFragment)getSupportFragmentManager()
                .findFragmentById(R.id.fragment_main));
        mainFragment.setUseGridLayout(mTwoPaneMode);
    }

    @Override
    public void initPresenter(Bundle savedInstanceState) {
        mMainPresenter = new MainActivityPresenterImpl(
                ThreadExecutor.getInstance(), // TODO inject dependencies
                MainThreadImpl.getInstance(),
                savedInstanceState,
                this
        );
    }

    @Override
    public void showProgress() {
        // not used
    }

    @Override
    public void hideProgress() {
        // not used
    }

    @Override
    public void showError(String message) {
        // not used
    }

    @Override
    public boolean getUiMode() {
        return findViewById(R.id.movie_detail_container) != null;
    }
}
