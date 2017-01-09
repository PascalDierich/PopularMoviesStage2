package com.pascaldierich.popularmoviesstage2.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.model.Review;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.ReviewView;

import java.util.ArrayList;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewView> {
	private static final String LOG_TAG = ReviewAdapter.class.getSimpleName();

	private ArrayList<Review> mReviews;

	public ReviewAdapter(ArrayList<Review> reviews) {
		this.mReviews = reviews;
	}

	public void setResults(ArrayList<Review> results) {
		Log.d(LOG_TAG, "setResults: size of results: " + results.size());
		this.mReviews = results;
	}

	@Override
	public ReviewView onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater
				.from(parent.getContext())
				.inflate(R.layout.review_layout, parent, false);

		return new ReviewView(itemView);
	}

	@Override
	public void onBindViewHolder(ReviewView holder, int position) {
		if (getItemCount() == 0) {
			Log.d(LOG_TAG, "onBindViewHolder: getItemCount == 0");
			holder.mTextViewContent.setText("loading...");
			return;
		}
		Review review = mReviews.get(position);
		Log.d(LOG_TAG, "onBindViewHolder: should show now Content");
		holder.mTextViewContent.setText(review.getContent());
	}

	@Override
	public int getItemCount() {
		if (mReviews == null) {
			return 0;
		}
		return mReviews.size();
	}

	private static class ViewHolder {
		TextView sTextView;
	}
}
