package com.pascaldierich.popularmoviesstage2.presentation.presenters;

import android.support.annotation.Nullable;

import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.BasePresenter;

/**
 * Created by pascaldierich on 12.12.16.
 */

public interface DetailPresenter extends BasePresenter {

	interface View {
		int getSelectedMovieId();

		void showGivenData(DetailMovieObject movie);
	}

	void checkSelectedMovie();

	void getDetailMovieObject();

	void showGivenData(@Nullable DetailMovieObject movie);

	// Network
	void getTrailer(int id);

	void getReviews(int id);

	void saveAsFavorite(DetailMovieObject object);
}
