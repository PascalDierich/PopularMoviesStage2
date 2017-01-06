package com.pascaldierich.popularmoviesstage2.presentation.ui.model;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */
public class ImageViewThumbnail extends ImageView {
	private static final String LOG_TAG = ImageViewThumbnail.class.getSimpleName();

	public ImageViewThumbnail(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = View.MeasureSpec.getSize(widthMeasureSpec);
		int height = width * getDrawable().getIntrinsicHeight() / getDrawable().getIntrinsicWidth();
		setMeasuredDimension(width, height);
	}
}
