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
    private static final String LOG_TAG = "RestClient";

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static Retrofit s_retrofit;

    static {


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        s_retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static <T> T getService(Class<T> serviceClass) {
        Log.d(LOG_TAG, "getService: is initializing");
        return s_retrofit.create(serviceClass);
    }
}
