package com.pascaldierich.popularmoviesstage2.data.network.model;

import java.util.ArrayList;

/**
 * Created by pascaldierich on 11.12.16.
 */

public class Page {
    private static final String LOG_TAG = Page.class.getSimpleName();

    private int mPage;

    private ArrayList<Movie> mResults;

    public Page(int page, ArrayList<Movie> results) {
        this.mPage = page;
        this.mResults = results;
    }

    public int getPage() {
        return this.mPage;
    }

    public ArrayList<Movie> getmResults() {
        return this.mResults;
    }

    public void setPage(int page) {
        this.mPage = page;
    }

    public void setResults(ArrayList<Movie> results) {
        this.mResults = results;
    }
}
