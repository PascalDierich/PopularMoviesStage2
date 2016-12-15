package com.pascaldierich.popularmoviesstage2.presentation.presenters;

import android.content.SharedPreferences;

import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.BasePresenter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.BaseView;

/**
 * Created by pascaldierich on 08.12.16.
 */

public interface MainActivityPresenter extends BasePresenter {

	interface View extends BaseView {
		boolean getUiMode();

		void setTwoPaneMode(boolean twoPaneMode);

		SharedPreferences getPreferences();

		void restart();

	}

	boolean onMenuItemSelected(int id);

	void setUiMode();


}
