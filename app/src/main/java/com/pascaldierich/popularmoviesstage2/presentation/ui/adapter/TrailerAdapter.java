package com.pascaldierich.popularmoviesstage2.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.model.Trailer;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.TrailerView;
import com.pascaldierich.popularmoviesstage2.presentation.ui.callback.TrailerPlayButtonCallback;

import java.util.ArrayList;

/**
 * Created by Pascal Dierich on Dez, 2016.
 */
public class TrailerAdapter extends RecyclerView.Adapter<TrailerView> {
	private static final String LOG_TAG = TrailerAdapter.class.getSimpleName();

	private ArrayList<Trailer> mTrailers;

	private TrailerPlayButtonCallback mCallback;

	public TrailerAdapter(ArrayList<Trailer> trailerList, TrailerPlayButtonCallback c) {
		this.mTrailers = trailerList;
		this.mCallback = c;
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
			return;
		}
		final Trailer TRAILER = mTrailers.get(position);
		holder.mTextViewContent.setText(TRAILER.getTitle());

		holder.mImageButtonPlay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(LOG_TAG, "onClick: Play Button clicked");
				mCallback.playButtonPressed(TRAILER.getKey());
			}
		});
	}

	@Override
	public int getItemCount() {
		if (mTrailers == null) {
			return 0;
		}
		return mTrailers.size();
	}
}
