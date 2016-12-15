package com.pascaldierich.popularmoviesstage2.presentation.presenters;

import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.BasePresenter;

/**
 * Created by pascaldierich on 12.12.16.
 */

public interface DetailFragmentPresenter extends BasePresenter {

	interface View {
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
