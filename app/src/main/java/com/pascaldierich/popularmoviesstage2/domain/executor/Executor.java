package com.pascaldierich.popularmoviesstage2.domain.executor;

import com.pascaldierich.popularmoviesstage2.domain.interactors.base.AbstractInteractor;

/**
 * Created by pascaldierich on 08.12.16.
 */

public interface Executor {

    /**
     * This method should call the interactor's run method and thus start the interactor. This should be called
     * on a background thread as interactors might do lengthy operations.
     *
     * @param interactor The interactor to run.
     */
    void execute(final AbstractInteractor interactor);
}
