package com.pascaldierich.popularmoviesstage2.presentation.presenters;

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
		// TODO: Add your view methods
	}

	void movieSelected(int position);

	void preferencesChanged();

	// Data
	void getPopularMovies();

	void getTopRatedMovies();

	void getFavoriteMovies();

}
