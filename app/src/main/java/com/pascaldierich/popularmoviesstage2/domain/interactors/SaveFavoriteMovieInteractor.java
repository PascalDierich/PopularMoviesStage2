package com.pascaldierich.popularmoviesstage2.domain.interactors;

import com.pascaldierich.popularmoviesstage2.domain.interactors.base.Interactor;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public interface SaveFavoriteMovieInteractor extends Interactor {

	interface Callback {
		void onSaveFinish(boolean success);
	}

}
