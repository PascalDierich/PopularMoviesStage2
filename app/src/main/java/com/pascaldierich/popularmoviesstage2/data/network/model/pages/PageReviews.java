package com.pascaldierich.popularmoviesstage2.data.network.model.pages;

import com.google.gson.annotations.SerializedName;
import com.pascaldierich.popularmoviesstage2.data.network.model.Review;

import java.util.ArrayList;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class PageReviews {

	@SerializedName("page")
	private int mPage;

	@SerializedName("results")
	private ArrayList<Review> mResults;

	public PageReviews(int page, ArrayList<Review> results) {
		this.mPage = page;
		this.mResults = results;
	}

	public int getPage() {
		return this.mPage;
	}

	public ArrayList<Review> getResults() {
		return this.mResults;
	}
}
