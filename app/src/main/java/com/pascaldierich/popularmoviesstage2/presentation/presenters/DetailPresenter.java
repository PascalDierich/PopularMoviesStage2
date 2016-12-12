package com.pascaldierich.popularmoviesstage2.presentation.presenters;

import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.BasePresenter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.BaseView;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface DetailPresenter extends BasePresenter {

    interface View extends BaseView {

    }

    void getTrailer(int id);

    void getReviews(int id);

    void saveAsFavorite();
}
