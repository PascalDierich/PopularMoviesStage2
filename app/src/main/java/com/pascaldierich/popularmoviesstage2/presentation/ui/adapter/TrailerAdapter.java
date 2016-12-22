package com.pascaldierich.popularmoviesstage2.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.model.Trailer;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.TrailerView;

import java.util.ArrayList;

/**
 * Created by Pascal Dierich on Dez, 2016.
 */
public class TrailerAdapter extends RecyclerView.Adapter<TrailerView> {
	private static final String LOG_TAG = TrailerAdapter.class.getSimpleName();

	private ArrayList<Trailer> mTrailers;

	public TrailerAdapter(ArrayList<Trailer> trailerList) {
		this.mTrailers = trailerList;
	}

	public void setResults(ArrayList<Trailer> results) {
		Log.d(LOG_TAG, "setResults: size of results: " + results.size());
		this.mTrailers = results;
	}

	@Override
	public TrailerView onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater
				.from(parent.getContext())
				.inflate(R.layout.trailer_layout, parent, false);

		return new TrailerView(itemView);
	}

	@Override
	public void onBindViewHolder(TrailerView holder, int position) {
		if (getItemCount() == 0) {
			Log.d(LOG_TAG, "onBindViewHolder: getItemCount == 0");
			holder.mTextViewContent.setText("loading...");
			return;
		}
		Trailer trailer = mTrailers.get(position);
		Log.d(LOG_TAG, "onBindViewHolder: should show now Title");
		holder.mTextViewContent.setText(trailer.getTitle());
	}

	@Override
	public int getItemCount() {
		if (mTrailers == null) {
			return 0;
		}
		return mTrailers.size();
	}

	private static class ViewHolder {
		TextView sTextView;
	}
}
