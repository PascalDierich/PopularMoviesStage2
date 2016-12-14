package com.pascaldierich.popularmoviesstage2.presentation.presenters;

import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.BasePresenter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.BaseView;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface DetailActivityPresenter extends BasePresenter {

    interface View extends BaseView {
        int getSelectedMovieId();

        void showGivenData(DetailMovieObject movie);
    }

    void checkSelectedMovie();

    void getDetailMovieObject();

    void showGivenData();

    // Network
    void getTrailer(int id);

    void getReviews(int id);

    void saveAsFavorite();
}
