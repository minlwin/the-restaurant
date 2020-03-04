package com.jdc.restaurant.client.api;

import java.util.Map;

import com.jdc.restaurant.client.dto.SummaryDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface SummaryApi {

	@GET("summary/chart")
	Call<Map<String, Map<String, Integer>>> getChartData(@QueryMap Map<String, String> query);

	@GET("summary")
	Call<SummaryDTO> getSummary();
}
