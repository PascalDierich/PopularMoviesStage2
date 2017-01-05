package com.pascaldierich.popularmoviesstage2.presentation.ui.model;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

/**
 * Created by pascaldierich on 13.12.16.
 */

public class GridItem {
	private String image;
	private Bitmap bitmap;



	public GridItem(@Nullable String image, @Nullable Bitmap bitmap) {
		super();
		this.image = image;
		this.bitmap = bitmap;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
}
