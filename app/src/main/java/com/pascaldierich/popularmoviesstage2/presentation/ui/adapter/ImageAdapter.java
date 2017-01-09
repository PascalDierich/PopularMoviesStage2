package com.pascaldierich.popularmoviesstage2.presentation.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.presentation.ui.model.GridItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public class ImageAdapter extends ArrayAdapter<GridItem> {
	private static final String LOG_TAG = ImageAdapter.class.getSimpleName();

	private Context mContext;
	private int mLayoutResourceId;
	private ArrayList<GridItem> mGridData = new ArrayList<>();

	public ImageAdapter(Context c, int layoutResourceId, ArrayList<GridItem> gridData) {
		super(c, layoutResourceId, gridData);
		this.mContext = c;
		this.mLayoutResourceId = layoutResourceId;
		this.mGridData = gridData;
	}

	@Override
	@NonNull
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			convertView = inflater.inflate(mLayoutResourceId, parent, false);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView.findViewById(R.id.image_for_adapter);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (mGridData.get(position).getBitmap() != null) {
			holder.imageView.setImageBitmap(mGridData.get(position).getBitmap());
		} else {
			GridItem imgURL = mGridData.get(position);

			String imageURL = mContext.getString(R.string.image_base_url)
					+ imgURL.getImage()
					+ "?api_key="
					+ mContext.getString(R.string.api_key);

			Picasso.with(mContext)
					.load(imageURL)
					.placeholder(R.mipmap.ic_launcher)
					.fit()
					.into(holder.imageView);
		}

		return convertView;
	/**/
	}

	private static class ViewHolder {
		ImageView imageView;
	}
}
