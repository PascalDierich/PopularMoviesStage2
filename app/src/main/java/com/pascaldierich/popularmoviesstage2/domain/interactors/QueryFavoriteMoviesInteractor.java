package com.pascaldierich.popularmoviesstage2.domain.interactors;

import com.pascaldierich.popularmoviesstage2.domain.interactors.base.Interactor;

import java.util.ArrayList;

/**
 * Created by pascaldierich on 12.12.16.
 */

public interface QueryFavoriteMoviesInteractor extends Interactor {

    interface Callback {
        void onQueryFinished(ArrayList<String[]> faveMovies);
    }

}
