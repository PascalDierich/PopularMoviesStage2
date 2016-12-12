package com.pascaldierich.popularmoviesstage2.data.network.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by pascaldierich on 11.12.16.
 */

public class Page {
    private static final String LOG_TAG = Page.class.getSimpleName();

    @SerializedName("page")
    private int mPage;

    @SerializedName("results")
    private ArrayList<Movie> mResults;

    public Page(int page, ArrayList<Movie> results) {
        this.mPage = page;
        this.mResults = results;
    }

    public int getPage() {
        return this.mPage;
    }

    public ArrayList<Movie> getResults() {
        return this.mResults;
    }
}
