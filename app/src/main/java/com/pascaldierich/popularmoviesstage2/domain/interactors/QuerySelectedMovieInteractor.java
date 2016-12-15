package com.pascaldierich.popularmoviesstage2.domain.interactors;

import com.pascaldierich.popularmoviesstage2.domain.interactors.base.Interactor;

/**
 * Created by Pascal Dierich on Dez, 2016.
 */

public interface QuerySelectedMovieInteractor extends Interactor {

	interface Callback {
		void onLoadFinished(String[] movie);
	}

}
