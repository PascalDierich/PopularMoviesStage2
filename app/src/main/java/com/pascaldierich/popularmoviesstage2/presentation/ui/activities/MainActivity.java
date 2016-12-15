package com.pascaldierich.popularmoviesstage2.presentation.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.domain.executor.impl.ThreadExecutor;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainActivityPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.impl.MainActivityPresenterImpl;
import com.pascaldierich.popularmoviesstage2.presentation.ui.fragments.DetailFragment;
import com.pascaldierich.popularmoviesstage2.presentation.ui.fragments.MainFragment;
import com.pascaldierich.popularmoviesstage2.threading.MainThreadImpl;
import com.pascaldierich.popularmoviesstage2.utils.ConstantsHolder;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {
	private static final String LOG_TAG = MainActivity.class.getSimpleName();
	private static final String DETAILFRAGMENT_TAG = "DetailFragmentTag";

	private MainActivityPresenter mMainPresenter;

	private boolean mTwoPaneMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ConstantsHolder.setApiKey(getString(R.string.api_key));
		Log.d(LOG_TAG, "onCreate: api_key = " + getString(R.string.api_key));

		initPresenter(savedInstanceState);
	}

	@Override
	public void setTwoPaneMode(boolean twoPaneMode) {
		this.mTwoPaneMode = twoPaneMode;
		ConstantsHolder.setTwoPaneMode(twoPaneMode);
		if (twoPaneMode) {
			Log.d(LOG_TAG, "setTwoPaneMode: true");
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.movie_detail_container, new DetailFragment(), DETAILFRAGMENT_TAG)
					.commit();
			Log.d(LOG_TAG, "setTwoPaneMode: DetailFragment set on movie_detail_container");
		} else {
			getSupportActionBar().setElevation(0f);
		}

		MainFragment mainFragment = ((MainFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_main));

		mainFragment.setUseGridLayout(this.mTwoPaneMode);
	}

	@Override
	public void initPresenter(Bundle savedInstanceState) {
		mMainPresenter = new MainActivityPresenterImpl(
				ThreadExecutor.getInstance(),
				MainThreadImpl.getInstance(),
				savedInstanceState,
				this
		);
	}

	@Override
	public Context getApplicationContext() {
		return this;
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
		// not used
	}

	@Override
	public SharedPreferences getPreferences() {
		return getApplicationContext().getSharedPreferences("", 1); // TODO: 15.12.16 String s &6 int i??
	}

	@Override
	public void restart() {
		Log.d(LOG_TAG, "restart: ");
		super.onRestart();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		super.onStop();

		if (!mMainPresenter.onMenuItemSelected(id)) {
			super.onOptionsItemSelected(item);
		}

		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onPause() {
		Log.d(LOG_TAG, "onPause: ");
		super.onPause();
	}

	@Override
	public boolean getUiMode() {
		return findViewById(R.id.movie_detail_container) != null;
	}
}
