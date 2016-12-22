package com.pascaldierich.popularmoviesstage2.presentation.converters.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pascaldierich.popularmoviesstage2.R;

/**
 * Created by Pascal Dierich on Dez, 2016.
 */
public class ReviewView extends RecyclerView.ViewHolder {
	private static final String LOG_TAG = ReviewView.class.getSimpleName();

	public TextView mTextViewContent;

	public ReviewView(View itemView) {
		super(itemView);
		mTextViewContent = (TextView) itemView.findViewById(R.id.text_view_review_layout);
	}
}
