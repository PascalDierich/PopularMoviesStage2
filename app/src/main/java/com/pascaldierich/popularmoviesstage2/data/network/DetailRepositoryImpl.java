package com.pascaldierich.popularmoviesstage2.data.network;

import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageReviews;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageTrailers;
import com.pascaldierich.popularmoviesstage2.data.network.services.DetailService;
import com.pascaldierich.popularmoviesstage2.domain.repository.DetailInfoMoviesRepository;
import com.pascaldierich.popularmoviesstage2.utils.ConstantsHolder;

import java.io.IOException;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public class DetailRepositoryImpl implements DetailInfoMoviesRepository {

	private DetailService mClient;

	public DetailRepositoryImpl() {
		this.mClient = RestClient.getService(DetailService.class);
	}

	@Override
	public PageTrailers downloadTrailer(int id) {
		PageTrailers page;

		try {
			page = mClient.getTrailers(id, ConstantsHolder.getApiKey()).execute().body();

			return page;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public PageReviews downloadReviews(int id) {
		PageReviews page;
		try {
			page = mClient.getReviews(id, ConstantsHolder.getApiKey()).execute().body();

			return page;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
