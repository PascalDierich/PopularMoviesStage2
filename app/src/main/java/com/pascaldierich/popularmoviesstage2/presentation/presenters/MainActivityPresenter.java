package com.pascaldierich.popularmoviesstage2.presentation.presenters;

import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.BasePresenter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.BaseView;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public interface MainActivityPresenter extends BasePresenter {

	interface View extends BaseView {
		boolean getUiMode();

		void setTwoPaneMode(boolean twoPaneMode);

	}

	void setUiMode();

}
