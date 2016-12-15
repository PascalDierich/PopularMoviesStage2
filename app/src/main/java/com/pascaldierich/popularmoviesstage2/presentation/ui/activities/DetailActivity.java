package com.pascaldierich.popularmoviesstage2.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.DetailRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.data.storage.FavoriteRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.data.storage.SaveMovieRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.domain.executor.impl.ThreadExecutor;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.DetailPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.impl.DetailPresenterImpl;
import com.pascaldierich.popularmoviesstage2.presentation.ui.BaseView;
import com.pascaldierich.popularmoviesstage2.threading.MainThreadImpl;

public class DetailActivity extends AppCompatActivity implements BaseView, DetailPresenter.View {
	private static final String LOG_TAG = DetailActivity.class.getSimpleName();

	// Presenter
	private DetailPresenter mPresenter;

	// View Components
	private TextView mTextViewTitle;
	private TextView mTextViewRelease;
	private TextView mTextViewLength;
	private TextView mTextViewRating;
	private TextView mTextViewDescription;

	private ImageButton mImageButtonFavorite;

	private ImageView mImageViewThumbnail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		initViews();
		initPresenter(savedInstanceState, null);

	}

	public void initViews() {
		this.mTextViewTitle = (TextView) findViewById(R.id.textView_title);
		this.mTextViewRelease = (TextView) findViewById(R.id.textView_release);
		this.mTextViewLength = (TextView) findViewById(R.id.textView_length);
		this.mTextViewRating = (TextView) findViewById(R.id.textView_rating);
		this.mTextViewDescription = (TextView) findViewById(R.id.textView_description);
		this.mImageButtonFavorite = (ImageButton) findViewById(R.id.imageButton_favorite);
		this.mImageViewThumbnail = (ImageView) findViewById(R.id.imageView_thumbnail);
	}

	@Override
	public void showGivenData(DetailMovieObject movie) {
		this.mTextViewLength.setText("hallo");
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
//        this.mTextViewLength.setText(movie.getmLength);
//        this.mImageViewThumbnail.setImageBitmap(null); // TODO: 14.12.16 get Bitmap -> get Bitmap from Picasso and save it somewhere (?) 

	}

	@Override
	public void showProgress() {

	}

	@Override
	public void hideProgress() {

	}

	@Override
	public void showError(String message) {

	}

	@Override
	public void initPresenter(Bundle savedInstanceState, Bundle arguments) {
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
	public int getSelectedMovieId() {
		return getIntent().getIntExtra(getString(R.string.intent_string_key), R.integer.error_internCommunication_noSelectedMovie);
		// TODO: 14.12.16 save String key in strings.xml;
	}
}
