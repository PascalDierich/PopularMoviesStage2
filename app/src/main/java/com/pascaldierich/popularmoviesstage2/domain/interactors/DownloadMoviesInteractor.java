package com.pascaldierich.popularmoviesstage2.domain.interactors;

import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageMovies;
import com.pascaldierich.popularmoviesstage2.domain.interactors.base.Interactor;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public interface DownloadMoviesInteractor extends Interactor {

	interface Callback {
		void onDownloadFinish(PageMovies movies);
	}

}
