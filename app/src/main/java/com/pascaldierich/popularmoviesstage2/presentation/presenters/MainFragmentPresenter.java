package com.pascaldierich.popularmoviesstage2.presentation.presenters;

import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.BasePresenter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.BaseView;

/**
 * Created by pascaldierich on 12.12.16.
 */

public interface MainFragmentPresenter extends BasePresenter {

    interface View extends BaseView {
        void showMovies();


        // TODO: Add your view methods
    }

    void getPopularMovies();

    void getTopRatedMovies();

    void getFavoriteMovies();

}
