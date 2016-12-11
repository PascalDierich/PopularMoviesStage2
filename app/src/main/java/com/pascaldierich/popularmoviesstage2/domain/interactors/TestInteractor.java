package com.pascaldierich.popularmoviesstage2.domain.interactors;

import com.pascaldierich.popularmoviesstage2.domain.interactors.base.Interactor;

import java.net.Inet4Address;

/**
 * Created by pascaldierich on 11.12.16.
 */

public interface TestInteractor extends Interactor {

    interface Callback {
        void onTestFinish(int i);
    }

}
