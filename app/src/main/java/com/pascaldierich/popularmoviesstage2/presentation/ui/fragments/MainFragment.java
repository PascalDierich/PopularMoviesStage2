package com.pascaldierich.popularmoviesstage2.presentation.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.MoviesRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.data.storage.FavoriteRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.domain.executor.impl.ThreadExecutor;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainFragmentPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.impl.MainFragmentPresenterImpl;
import com.pascaldierich.popularmoviesstage2.presentation.ui.activities.DetailActivity;
import com.pascaldierich.popularmoviesstage2.presentation.ui.adapter.ImageAdapter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.model.GridItem;
import com.pascaldierich.popularmoviesstage2.threading.MainThreadImpl;

import java.util.ArrayList;


/**
 * Created by pascaldierich on 12.12.16.
 */

public class MainFragment extends Fragment implements MainFragmentPresenter.View {
    private static final String LOG_TAG = MainFragment.class.getSimpleName();

    private boolean mTwoPaneMode;

    private MainFragmentPresenter mPresenter;

    private ProgressBar mProgressBar;

    private ImageAdapter mImageAdapter;

    private GridView mGridView;

    private View mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(false);

        initPresenter(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_main, container, false);
        mProgressBar = (ProgressBar) mRootView.findViewById(R.id.progress_bar);

        return mRootView;
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
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
        mPresenter = new MainFragmentPresenterImpl(
                ThreadExecutor.getInstance(),
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
    public void showMovies(ArrayList<GridItem> movies) {
        if (this.mTwoPaneMode) {
            mImageAdapter = new ImageAdapter(getActivity(), R.layout.list_view_layout, movies);
            Log.d(LOG_TAG, "showMovies: selected list_view_layout");
        } else {
            mImageAdapter = new ImageAdapter(getActivity(), R.layout.grid_view_layout, movies);
            Log.d(LOG_TAG, "showMovies: selected grid_view_layout");
        }

        mGridView = (GridView) mRootView.findViewById(R.id.grid_view_main_fragment);
        mGridView.setAdapter(mImageAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // position in GridView = position in ArrayList in ConstantsHolder
//                DetailMovieObject clickedMovie = ConstantsHolder.getDownloadedData().get((int) id);

                mPresenter.movieSelected(position);
                // TODO: 14.12.16 give clickedMovie to Presenter and let him do the rest (start Activity/fragment etc) 
                // TODO: 14.12.16 get Data from specific view ?!?! 
            }
        });
    }

    @Override
    public void startDetailActivity(int position) {
        Intent intent = new Intent(getActivity(), DetailActivity.class)
                .putExtra(getString(R.string.intent_string_key), position);
        startActivity(intent);
    }

    @Override
    public boolean getTwoPaneMode() {
        return mTwoPaneMode;
    }
    
}
