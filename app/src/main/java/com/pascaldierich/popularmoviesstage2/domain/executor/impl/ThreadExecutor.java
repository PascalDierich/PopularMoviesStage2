package com.pascaldierich.popularmoviesstage2.domain.executor.impl;

import android.os.Looper;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.base.AbstractInteractor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Created by pascaldierich on 08.12.16.
 */

public class ThreadExecutor implements Executor {
    private static final String LOG_TAG = "ThreadExecutor";

    // This is a singleton
    private static volatile ThreadExecutor sThreadExecutor;

    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>();

    private ThreadPoolExecutor mThreadPoolExecutor;

    public ThreadExecutor() {
        long keepAlive = KEEP_ALIVE_TIME;
        mThreadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                keepAlive,
                TIME_UNIT,
                WORK_QUEUE);
    }

    @Override
    public void execute(final AbstractInteractor interactor) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.d(LOG_TAG, "execute: Looper on Main");
        } else {
            Log.d(LOG_TAG, "execute: Looper NOT on Main");
        }
        Log.d(LOG_TAG, "execute: got called");
        mThreadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                // run the main logic
                Log.d(LOG_TAG, "run: befor interactor.run");
                interactor.run();
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    Log.d(LOG_TAG, "run: is on Main Thread");
                } else {
                    Log.d(LOG_TAG, "run: is NOT on Main Thread");
                }


                // mark it as finished
                interactor.onFinished();
            }
        });
    }

    /**
     * Returns a singleton instance of this executor. If the executor is not initialized then it initializes it and returns
     * the instance.
     */
    public static Executor getInstance() {
        if (sThreadExecutor == null) {
            sThreadExecutor = new ThreadExecutor();
        }

        return sThreadExecutor;
    }
}
