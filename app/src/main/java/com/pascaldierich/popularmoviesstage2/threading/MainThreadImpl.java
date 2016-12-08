package com.pascaldierich.popularmoviesstage2.threading;

import android.os.Handler;
import android.os.Looper;

import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;

/**
 * Created by pascaldierich on 08.12.16.
 */

public class MainThreadImpl implements MainThread {

    private static MainThread sMainThread;

    private Handler mHandler;

    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static MainThread getInstance() {
        if (sMainThread == null) {
            sMainThread = new MainThreadImpl();
        }

        return sMainThread;
    }
}
