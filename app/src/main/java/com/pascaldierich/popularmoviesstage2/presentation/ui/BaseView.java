package com.pascaldierich.popularmoviesstage2.presentation.ui;

import android.os.Bundle;

/**
 * Created by pascaldierich on 08.12.16.
 */

public interface BaseView {
    /**
     * This is a general method used for showing some kind of progress during a background task. For example, this
     * method should show a progress bar and/or disable buttons before some background work starts.
     */
    void showProgress();

    /**
     * This is a general method used for hiding progress information after a background task finishes.
     */
    void hideProgress();

    /**
     * This method is used for showing error messages on the UI.
     *
     * @param message The error message to be displayed.
     */
    void showError(String message);

    void initPresenter(Bundle savedInstanceState);

}
