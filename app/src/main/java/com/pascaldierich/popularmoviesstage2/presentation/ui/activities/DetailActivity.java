package com.pascaldierich.popularmoviesstage2.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.DetailRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.data.network.model.Trailer;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageReviews;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageTrailers;
import com.pascaldierich.popularmoviesstage2.data.storage.FavoriteRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.data.storage.SaveMovieRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.domain.executor.impl.ThreadExecutor;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.DetailPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.impl.DetailPresenterImpl;
import com.pascaldierich.popularmoviesstage2.presentation.ui.BaseView;
import com.pascaldierich.popularmoviesstage2.presentation.ui.adapter.ReviewAdapter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.adapter.TrailerAdapter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.callback.TrailerPlayButtonCallback;
import com.pascaldierich.popularmoviesstage2.threading.MainThreadImpl;
import com.pascaldierich.popularmoviesstage2.utils.ConstantsHolder;
import com.pascaldierich.popularmoviesstage2.utils.Utility;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements BaseView, DetailPresenter.View,
		TrailerPlayButtonCallback {
	private static final String LOG_TAG = DetailActivity.class.getSimpleName();

	// Presenter
	private DetailPresenter mPresenter;

	// View Components
	private TextView mTextViewTitle;
	private TextView mTextViewRelease;
	private TextView mTextViewLength;
	private TextView mTextViewRating;
	private TextView mTextViewDescription;

	private TextView mTextViewTrailerTitle;
	private TextView mTextViewReviewTitle;

	private RecyclerView mRecyclerViewTrailers;
	private RecyclerView mRecyclerViewReviews;

	// Buttons
	private ImageButton mImageButtonFavorite;

	// ImageView for Thumbnail
	private ImageView mImageViewThumbnail;

	// Bitmap of Thumbnail
	private Bitmap mBitmap;

	// Adapter
	private TrailerAdapter mTrailerAdapter;
	private ReviewAdapter mReviewAdapter;

	// Trailer
	private ArrayList<Trailer> mTrailers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		initViews();
		initPresenter(savedInstanceState, (DetailMovieObject) getIntent()
				.getBundleExtra(getString(R.string.extra_intent_key))
				.get(getString(R.string.parcelable_detail_movie_object_key)));
	}

	public void initViews() {
		this.mTextViewTitle = (TextView) findViewById(R.id.textView_title);
		this.mTextViewRelease = (TextView) findViewById(R.id.textView_release);
		this.mTextViewLength = (TextView) findViewById(R.id.textView_length);
		this.mTextViewRating = (TextView) findViewById(R.id.textView_rating);
		this.mTextViewDescription = (TextView) findViewById(R.id.textView_description);
		this.mImageButtonFavorite = (ImageButton) findViewById(R.id.imageButton_favorite);

		this.mImageViewThumbnail = (ImageView) findViewById(R.id.imageView_thumbnail);
		this.mRecyclerViewTrailers = (RecyclerView) findViewById(R.id.recycler_view_trailer);
		this.mRecyclerViewReviews = (RecyclerView) findViewById(R.id.recycler_view_review);

		this.mTrailerAdapter = new TrailerAdapter(null, this);
		this.mRecyclerViewTrailers.setLayoutManager(new LinearLayoutManager(this));
		this.mRecyclerViewTrailers.setAdapter(this.mTrailerAdapter);

		this.mReviewAdapter = new ReviewAdapter(null);
		this.mRecyclerViewReviews.setLayoutManager(new LinearLayoutManager(this));
		this.mRecyclerViewReviews.setAdapter(this.mReviewAdapter);

		this.mTextViewTrailerTitle = (TextView) findViewById(R.id.textView_Trailer_Title);
		this.mTextViewReviewTitle = (TextView) findViewById(R.id.textView_Review_Title);
	}

	@Override
	public void showGivenData(final DetailMovieObject movie) {
		this.mTextViewLength.setText("hallo"); // TODO: 05.01.17 delete "hallo" and get some real data 
		try {
			this.mTextViewTitle.setText(movie.getmTitle());
		} catch (NullPointerException npe) {
			Log.d(LOG_TAG, "showGivenData: NullPointerException when reading out title");
		}
		try {
			this.mTextViewRelease.setText(movie.getmRelease());
		} catch (NullPointerException npe) {
			Log.d(LOG_TAG, "showGivenData: NullPointerException when reading out release");
		}
		try {
			this.mTextViewRating.setText(movie.getmRating() + "/10"); // TODO: 14.12.16 save in strings.xml
		} catch (NullPointerException npe) {
			Log.d(LOG_TAG, "showGivenData: NullPointerException when reading out rating");
		}
		try {
			this.mTextViewDescription.setText(movie.getmDescription());
		} catch (NullPointerException npe) {
			Log.d(LOG_TAG, "showGivenData: NullPointerException when reading out description");
		}

		if (ConstantsHolder.bitmapIsNull()) {
			getBitmap(movie.getmPosterPath());
		} else {
			mImageViewThumbnail.setImageBitmap(ConstantsHolder.getBitmap());
		}

		this.mImageButtonFavorite.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				movie.setThumbnail(mBitmap);

				if (mTrailers == null) {
					movie.setTrailers(null);
				} else {
					for (Trailer trailer : mTrailers) {
						movie.setTrailers(new String[] {
								trailer.getKey()
						});
					}
				}
				mPresenter.saveAsFavorite(movie);
			}
		});

	}

	private Target loadTarget;

	private void getBitmap(String url) {
		url = getString(R.string.image_base_url)
				+ url
				+ "?api_key="
				+ getString(R.string.api_key);
		loadTarget = new Target() {
			@Override
			public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
				showBitmap(bitmap);
			}

			@Override
			public void onBitmapFailed(Drawable errorDrawable) {
				Log.e(LOG_TAG, "onBitmapFailed: couldnt download Bitmap");
			}

			@Override
			public void onPrepareLoad(Drawable placeHolderDrawable) {
				// not used
			}
		};

		Picasso.with(this).load(url).into(loadTarget);
	}

	@Override
	public void showTrailerProgress() {

	}

	@Override
	public void showReviewProgress() {

	}

	@Override
	public void showTrailer(PageTrailers results) {
		this.mTrailers = results.getResults();
		this.mTrailerAdapter.setResults(this.mTrailers);
		this.mTrailerAdapter.notifyDataSetChanged();
		if (results.getResults().size() == 0) {
			this.mTextViewTrailerTitle.setVisibility(View.GONE);
		}
	}

	@Override
	public void showReview(PageReviews results) {
		this.mReviewAdapter.setResults(results.getResults());
		this.mReviewAdapter.notifyDataSetChanged();
		if (results.getResults().size() == 0) {
			this.mTextViewReviewTitle.setVisibility(View.GONE);
		}
	}
	
	@Override
	public void startNewActivity(Intent i) {
		startActivity(i);
	}
	
	private void showBitmap(Bitmap bitmap) {
		this.mBitmap = bitmap;
		this.mImageViewThumbnail.setImageBitmap(this.mBitmap);
	}

	@Override
	public void showProgress() {
		// not used
	}

	@Override
	public void hideProgress() {
		// not used
	}

	@Override
	public void showError(String message) {

	}

	@Override
	public void initPresenter(Bundle savedInstanceState, DetailMovieObject arguments) {
		this.mPresenter = new DetailPresenterImpl(
				ThreadExecutor.getInstance(),
				MainThreadImpl.getInstance(),
				savedInstanceState,
				this,
				new DetailRepositoryImpl(),
				new SaveMovieRepositoryImpl(getApplicationContext()),
				new FavoriteRepositoryImpl(getApplicationContext()),
				arguments
		);
	}

	@Override
	public void playButtonPressed(String key) {
		mPresenter.onPlayPressed(getString(R.string.base_url_youtube), key);
	}

	@Override
	public boolean checkConnection() {
		return Utility.checkConnection((ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE));
	}
}
