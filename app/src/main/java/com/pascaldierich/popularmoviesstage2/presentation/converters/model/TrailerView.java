package com.pascaldierich.popularmoviesstage2.presentation.converters.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pascaldierich.popularmoviesstage2.R;

/**
 * Created by Pascal Dierich on Dez, 2016.
 */
public class TrailerView extends RecyclerView.ViewHolder {
	private static final String LOG_TAG = TrailerView.class.getSimpleName();

	public TextView mTextViewContent;

	public TrailerView(View itemView) {
		super(itemView);
		mTextViewContent = (TextView) itemView.findViewById(R.id.text_view_trailer_layout);
	}
}
