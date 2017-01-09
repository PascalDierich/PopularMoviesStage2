package com.pascaldierich.popularmoviesstage2.data.network.model.pages;

import com.google.gson.annotations.SerializedName;
import com.pascaldierich.popularmoviesstage2.data.network.model.Trailer;

import java.util.ArrayList;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public class PageTrailers {

	@SerializedName("page")
	private int mPage;

	@SerializedName("results")
	private ArrayList<Trailer> mResults;

	public PageTrailers(int page, ArrayList<Trailer> results) {
		this.mPage = page;
		this.mResults = results;
	}

	public int getPage() {
		return this.mPage;
	}

	public ArrayList<Trailer> getResults() {
		return this.mResults;
	}
}
