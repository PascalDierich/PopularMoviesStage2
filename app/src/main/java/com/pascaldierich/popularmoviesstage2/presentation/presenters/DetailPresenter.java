package com.pascaldierich.popularmoviesstage2.presentation.presenters;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageReviews;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageTrailers;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.BasePresenter;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public interface DetailPresenter extends BasePresenter {

	interface View {

		void showGivenData(DetailMovieObject movie);

		void showTrailerProgress();

		void showReviewProgress();

		void showTrailer(PageTrailers results);

		void showReview(PageReviews results);

		void startNewActivity(Intent i);

		boolean checkConnection();
	}

	void showGivenData(@Nullable DetailMovieObject movie);

	void onPlayPressed(String base_url, String key);

	// Network
	void getTrailer(int id);

	void getReviews(int id);

	void saveAsFavorite(DetailMovieObject object);
}
