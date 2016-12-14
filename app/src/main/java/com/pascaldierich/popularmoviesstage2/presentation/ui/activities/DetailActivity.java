package com.pascaldierich.popularmoviesstage2.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.DetailRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.data.storage.SaveMovieRepositoryImpl;
import com.pascaldierich.popularmoviesstage2.domain.executor.impl.ThreadExecutor;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.DetailActivityPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.impl.DetailActivityPresenterImpl;
import com.pascaldierich.popularmoviesstage2.presentation.ui.BaseView;
import com.pascaldierich.popularmoviesstage2.threading.MainThreadImpl;
import com.pascaldierich.popularmoviesstage2.utils.ErrorCodes;

public class DetailActivity extends AppCompatActivity implements BaseView, DetailActivityPresenter.View {
    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    // Presenter
    private DetailActivityPresenter mPresenter;

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

        Log.d(LOG_TAG, "onCreate: Im here bitches");

        initPresenter(savedInstanceState);
        initViews();
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
        this.mTextViewTitle.setText(movie.getmTitle());
        this.mTextViewRelease.setText(movie.getmRelease());
//        this.mTextViewLength.setText(movie.getmLength);
        this.mTextViewRating.setText(movie.getmRating() + "/10"); // TODO: 14.12.16 save in strings.xml 
        this.mTextViewDescription.setText(movie.getmDescription());
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
    public void initPresenter(Bundle savedInstanceState) {
         this.mPresenter = new DetailActivityPresenterImpl(
                 ThreadExecutor.getInstance(),
                 MainThreadImpl.getInstance(),
                 savedInstanceState,
                 this,
                 new DetailRepositoryImpl(),
                 new SaveMovieRepositoryImpl(getApplicationContext())
         );
    }

    @Override
    public int getSelectedMovieId() {
        return getIntent().getIntExtra(getString(R.string.intent_string_key), ErrorCodes.internCommunication.NO_SELECTED_MOVIE);
        // TODO: 14.12.16 save String key in strings.xml;
    }
}
