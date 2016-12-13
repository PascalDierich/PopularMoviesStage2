package com.pascaldierich.popularmoviesstage2.presentation.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.presentation.ui.model.GridItem;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by pascaldierich on 13.12.16.
 */

public class ImageAdapter extends ArrayAdapter<GridItem> {
    private static final String LOG_TAG = ImageAdapter.class.getSimpleName();

    private Context context;
    private int layoutResourceId;
    private ArrayList<GridItem> gridData = new ArrayList<GridItem>();

    public ImageAdapter(Context c, int layoutResourceId, ArrayList<GridItem> gridData) {
        super(c, layoutResourceId, gridData);
        this.context = c;
        this.layoutResourceId = layoutResourceId;
        this.gridData = gridData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.image_for_adapter);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GridItem imgURL = gridData.get(position);



        String imageURL = context.getString(R.string.image_base_url)
                + imgURL.getImage()
                + "?api_key="
                + context.getString(R.string.api_key);

        Log.d(LOG_TAG, "getView: imURL = " + imageURL);

        Picasso.with(context).load(imageURL)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(holder.imageView);

        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView;
    }
}
