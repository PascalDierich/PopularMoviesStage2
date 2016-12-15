package com.pascaldierich.popularmoviesstage2.data.network.model.pages;

import com.google.gson.annotations.SerializedName;
import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;

import java.util.ArrayList;

/**
 * Created by pascaldierich on 11.12.16.
 */

public class PageMovies {

	@SerializedName("page")
	private int mPage;

	@SerializedName("results")
	private ArrayList<Movie> mResults;

	public PageMovies(int page, ArrayList<Movie> results) {
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
