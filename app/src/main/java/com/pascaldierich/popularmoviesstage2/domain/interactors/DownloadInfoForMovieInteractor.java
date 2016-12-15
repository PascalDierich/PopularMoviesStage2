package com.pascaldierich.popularmoviesstage2.domain.interactors;

import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageReviews;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageTrailers;
import com.pascaldierich.popularmoviesstage2.domain.interactors.base.Interactor;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface DownloadInfoForMovieInteractor extends Interactor {

	interface Callback {
		void onDownloadTrailerFinish(PageTrailers pageTrailers);

		void onDownloadReviewFinish(PageReviews pageReviews);
	}
}
