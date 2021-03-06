package com.pascaldierich.popularmoviesstage2.presentation.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.DetailRepositoryImpl;
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
import com.pascaldierich.popularmoviesstage2.utils.Utility;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public class DetailFragment extends Fragment implements BaseView,
		DetailPresenter.View,
		TrailerPlayButtonCallback {
	private static final String LOG_TAG = DetailFragment.class.getSimpleName();

	private DetailPresenter mPresenter;

	// View Components
	private TextView mTextViewTitle;
	private TextView mTextViewRelease;
	private TextView mTextViewLength;
	private TextView mTextViewRating;
	private TextView mTextViewDescription;

	private TextView mTextViewTrailerTitle;
	private TextView mTextViewReviewTitle;

	private View mRootView;

	// Buttons
	private ImageButton mImageButtonFavorite;

	private ImageView mImageViewThumbnail;

	private RecyclerView mRecyclerViewTrailers;
	private RecyclerView mRecyclerViewReviews;

	private Bundle mSavedInstanceState;

	// Thumbnail Bitmap
	private Bitmap mBitmap;

	// Adapter
	private TrailerAdapter mTrailerAdapter;
	private ReviewAdapter mReviewAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(false);
		this.mSavedInstanceState = savedInstanceState;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceBundle) {
		View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
		rootView = initViews(rootView);

		try {
			initPresenter(mSavedInstanceState, (DetailMovieObject) getArguments().get(getString(R.string.parcelable_detail_movie_object_key)));
		} catch (NullPointerException e) {
			Log.e(LOG_TAG, "onCreateView: NullPointerException" + "\n" +
					" --> " + e.fillInStackTrace());
			initPresenter(mSavedInstanceState, null);
		}


		return rootView;
	}


	@Override
	public void showProgress() {

	}

	@Override
	public void hideProgress() {

	}

	@Override
	public void showError(String code) {
		Log.d(LOG_TAG, "showError: ERROR Code: " + code);
	}

	@Override
	public Context getApplicationContext() {
		return getContext();
	}

	@Override
	public void initPresenter(Bundle savedInstanceState, DetailMovieObject arguments) {
		mPresenter = new DetailPresenterImpl(
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

	private View initViews(View rootView) {
		Log.d(LOG_TAG, "initViews: YEAH!");
		this.mTextViewTitle = (TextView) rootView.findViewById(R.id.textView_title);
		this.mTextViewRelease = (TextView) rootView.findViewById(R.id.textView_release);
		this.mTextViewLength = (TextView) rootView.findViewById(R.id.textView_length);
		this.mTextViewRating = (TextView) rootView.findViewById(R.id.textView_rating);
		this.mTextViewDescription = (TextView) rootView.findViewById(R.id.textView_description);
		this.mImageButtonFavorite = (ImageButton) rootView.findViewById(R.id.imageButton_favorite);
		
		this.mImageViewThumbnail = (ImageView) rootView.findViewById(R.id.imageView_thumbnail);
		this.mRecyclerViewTrailers = (RecyclerView) rootView.findViewById(R.id.recycler_view_trailer);
		this.mRecyclerViewReviews = (RecyclerView) rootView.findViewById(R.id.recycler_view_review);

		this.mTrailerAdapter = new TrailerAdapter(null, this);
		this.mRecyclerViewTrailers.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
		this.mRecyclerViewTrailers.setAdapter(this.mTrailerAdapter);

		this.mReviewAdapter = new ReviewAdapter(null);
		this.mRecyclerViewReviews.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
		this.mRecyclerViewReviews.setAdapter(this.mReviewAdapter);

		this.mTextViewTrailerTitle = (TextView) rootView.findViewById(R.id.textView_Trailer_Title);
		this.mTextViewReviewTitle = (TextView) rootView.findViewById(R.id.textView_Review_Title);

		this.mRootView = rootView;
		return rootView;
	}

	@Override
	public void showGivenData(final DetailMovieObject movie) {
		Log.d(LOG_TAG, "showGivenData: called with movie == null -> " + (movie == null));
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
			this.mTextViewRating.setText(movie.getmRating() + getString(R.string.rating_string));
		} catch (NullPointerException npe) {
			Log.d(LOG_TAG, "showGivenData: NullPointerException when reading out rating");
		}
		try {
			this.mTextViewDescription.setText(movie.getmDescription());
		} catch (NullPointerException npe) {
			Log.d(LOG_TAG, "showGivenData: NullPointerException when reading out description");
		}

		getBitmap(movie.getmPosterPath());

		this.mImageButtonFavorite.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				movie.setThumbnail(mBitmap);
				Log.d(LOG_TAG, "onClick: bitmap == null -> " + (mBitmap == null));
				mPresenter.saveAsFavorite(movie);
			}
		});
	}

	private Target loadTarget;

	@Override
	public void showTrailerProgress() {

	}

	@Override
	public void showReviewProgress() {
		
	}

	@Override
	public void showTrailer(PageTrailers results) {
		this.mTrailerAdapter.setResults(results.getResults());
		this.mTrailerAdapter.notifyDataSetChanged();
		if (results.getResults().size() == 0) {
			this.mTextViewTrailerTitle.setVisibility(View.GONE);
		}
	}

	@Override
	public void startNewActivity(Intent i) {
		startActivity(i);
	}

	@Override
	public void showReview(PageReviews results) {
		this.mReviewAdapter.setResults(results.getResults());
		this.mReviewAdapter.notifyDataSetChanged();
		if (results.getResults().size() == 0) {
			this.mTextViewReviewTitle.setVisibility(View.GONE);
		}
	}

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
				Log.e(LOG_TAG, "onBitmapFailed: could not download Bitmap");
			}

			@Override
			public void onPrepareLoad(Drawable placeHolderDrawable) {
				// not used
			}
		};

		Picasso.with(getApplicationContext()).load(url).into(loadTarget);
	}

	private void showBitmap(Bitmap bitmap) {
		this.mBitmap = bitmap;
		this.mImageViewThumbnail.setImageBitmap(this.mBitmap);
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
