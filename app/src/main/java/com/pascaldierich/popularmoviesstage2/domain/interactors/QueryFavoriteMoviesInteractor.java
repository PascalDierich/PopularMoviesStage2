package com.pascaldierich.popularmoviesstage2.domain.interactors;

import com.pascaldierich.popularmoviesstage2.data.storage.model.DataMovieObject;
import com.pascaldierich.popularmoviesstage2.domain.interactors.base.Interactor;

import java.util.ArrayList;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public interface QueryFavoriteMoviesInteractor extends Interactor {

	interface Callback {
		void onQueryFinished(ArrayList<DataMovieObject> faveMovies);
	}

}
