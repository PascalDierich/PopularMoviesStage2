package com.pascaldierich.popularmoviesstage2.presentation.presenters;

import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.BasePresenter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.BaseView;

/**
 * Created by pascaldierich on 08.12.16.
 */

public interface MainActivityPresenter extends BasePresenter {

    interface View extends BaseView {
        boolean getUiMode();
        void setTwoPaneMode(boolean twoPaneMode);

    }

    void setUiMode();


}
