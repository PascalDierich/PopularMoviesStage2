package com.pascaldierich.popularmoviesstage2.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public class Movie {

	@SerializedName("id")
	private String mId;

	@SerializedName("title")
	private String mTitle;

	@SerializedName("poster_path")
	private String mPosterPath;

	@SerializedName("overview")
	private String mDescription;

	@SerializedName("vote_average")
	private String mVoteAverage;

	@SerializedName("release_date")
	private String mReleaseDate;

	public Movie(String id, String title, String posterPath,
				 String description, String voteAverage, String releaseData) {
		mId = id;
		mTitle = title;
		mPosterPath = posterPath;
		mDescription = description;
		mVoteAverage = voteAverage;
		mReleaseDate = releaseData;
	}

	public String getId() {
		return mId;
	}

	public String getTitle() {
		return mTitle;
	}

	public String getPosterPath() {
		return mPosterPath;
	}

	public String getDescription() {
		return mDescription;
	}

	public String getVoteAverage() {
		return mVoteAverage;
	}

	public String getReleaseDate() {
		return mReleaseDate;
	}


}
