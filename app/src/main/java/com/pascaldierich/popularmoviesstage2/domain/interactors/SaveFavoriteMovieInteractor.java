package com.pascaldierich.popularmoviesstage2.domain.interactors;

import com.pascaldierich.popularmoviesstage2.domain.interactors.base.Interactor;

/**
 * Created by pascaldierich on 12.12.16.
 */

public interface SaveFavoriteMovieInteractor extends Interactor {

    interface Callback {
        void onSaveFinish(boolean success);
    }

}
