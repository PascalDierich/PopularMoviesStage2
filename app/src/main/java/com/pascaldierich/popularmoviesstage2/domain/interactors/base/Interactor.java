package com.pascaldierich.popularmoviesstage2.domain.interactors.base;

/**
 * Created by pascaldierich on 08.12.16.
 */

public interface Interactor {

    /**
     * This is the main method that starts an interactor. It will make sure that the interactor operation is done on a
     * background thread.
     */
    void execute();
}
