package com.pascaldierich.popularmoviesstage2.domain.interactors.impl;

import android.util.Log;

import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.DownloadInfoForMovieInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.base.AbstractInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.TestInteractor;

import retrofit2.Call;

/**
 * Created by pascaldierich on 11.12.16.
 */

public class TestInteractorImpl extends AbstractInteractor implements TestInteractor {
    private static final String LOG_TAG = TestInteractorImpl.class.getSimpleName();

    private TestInteractor.Callback mCallback;

    public TestInteractorImpl(Executor threadExecutor, MainThread mainThread, Callback callback) {
        super(threadExecutor, mainThread);
        this.mCallback = callback;

    }

    @Override
    public void run() {
        Log.d(LOG_TAG, "run: Test runs");
        int i = 0;
        while (i < 10) {
            Log.d(LOG_TAG, "run: i = " + i);
            i++;
        }

        final int a = i;

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onTestFinish(a);
            }
        });
    }
}
