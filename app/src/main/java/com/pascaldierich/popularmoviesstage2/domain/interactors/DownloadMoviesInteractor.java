package com.pascaldierich.popularmoviesstage2.domain.interactors;

import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;
import com.pascaldierich.popularmoviesstage2.domain.interactors.base.Interactor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface DownloadMoviesInteractor extends Interactor {

    interface Callback {
        void onDownloadFinish(ArrayList<Movie> movies);
    }

}
