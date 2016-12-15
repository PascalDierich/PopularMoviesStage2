package com.pascaldierich.popularmoviesstage2.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class Trailer {

	@SerializedName("id")
	private String mId;

	@SerializedName("key")
	private String mKey;

	@SerializedName("name")
	private String mTitle;

	@SerializedName("site")
	private String mSite;

	public Trailer(String id, String key, String title, String site) {
		this.mId = id;
		this.mKey = key;
		this.mTitle = title;
		this.mSite = site;
	}

	public String getId() {
		return this.mId;
	}

	public String getKey() {
		return this.mKey;
	}

	public String getTitle() {
		return this.mTitle;
	}

	public String getSite() {
		return this.mSite;
	}
}
