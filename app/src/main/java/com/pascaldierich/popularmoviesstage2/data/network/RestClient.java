package com.pascaldierich.popularmoviesstage2.data.network;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class RestClient {

    private static final String BASE_URL = "https://api.themoviedb.org";

    private static Retrofit s_retrofit;

    static {

        s_retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> T getService(Class<T> serviceClass) {
        return s_retrofit.create(serviceClass);
    }
}
