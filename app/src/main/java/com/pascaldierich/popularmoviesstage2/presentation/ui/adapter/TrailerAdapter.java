package com.pascaldierich.popularmoviesstage2.presentation.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.model.Trailer;

import java.util.ArrayList;

/**
 * Created by Pascal Dierich on Dez, 2016.
 */
public class TrailerAdapter extends RecyclerView.Adapter<Trailer> {
	private static final String LOG_TAG = TrailerAdapter.class.getSimpleName();
	
	
	// TODO: 19.12.16 implement Methods and write Adapter... 
	
	
	private Context mContext;
	private int mLayoutResourceId;
	private ArrayList<Trailer> mResults = new ArrayList<Trailer>();


	public TrailerAdapter(Context context, int layoutResourceId, ArrayList<Trailer> objects) {
		super(context, layoutResourceId, objects);

		this.mContext = context;
		this.mLayoutResourceId = layoutResourceId;
		this.mResults = objects;
	}

	@Override
	@NonNull
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			convertView = inflater.inflate(mLayoutResourceId, parent, false);
			holder = new ViewHolder();

			holder.sTextView = (TextView) convertView.findViewById(R.id.text_view_trailer_layout);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// TODO: 19.12.16 write data in TextView
		holder.sTextView.setText(mResults.get(position).getTitle());

		return convertView;
	}

	private static class ViewHolder {
		TextView sTextView;
	}
}
