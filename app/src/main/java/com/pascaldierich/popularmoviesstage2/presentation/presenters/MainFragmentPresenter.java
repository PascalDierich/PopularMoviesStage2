package com.pascaldierich.popularmoviesstage2.presentation.presenters;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.BasePresenter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.BaseView;
import com.pascaldierich.popularmoviesstage2.presentation.ui.model.GridItem;

import java.util.ArrayList;

/**
 * Created by pascaldierich on 12.12.16.
 */

public interface MainFragmentPresenter extends BasePresenter {

	interface View extends BaseView {
		void showMovies(ArrayList<GridItem> movies);

		boolean getTwoPaneMode();

		int getInitialPreferences();

		void startDetailActivity(int position);

		SharedPreferences getPreferences();

		Bundle restoreState();
	}

	boolean onMenuItemSelected(int id);

	void movieSelected(int position);

	// Data
	void getPopularMovies();

	void getTopRatedMovies();

	void getFavoriteMovies();

	// State
	void saveState(Object object);

	Bundle getState();

}
