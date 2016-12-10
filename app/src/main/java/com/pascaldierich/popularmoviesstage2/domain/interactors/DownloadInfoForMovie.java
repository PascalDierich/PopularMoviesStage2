package com.pascaldierich.popularmoviesstage2.domain.interactors;

import com.pascaldierich.popularmoviesstage2.domain.interactors.base.Interactor;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface DownloadInfoForMovie extends Interactor {

    interface Callback {
        void downloadTrailerFinish();

        void downloadReviewFinish();
    }
}
