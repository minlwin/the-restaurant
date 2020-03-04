package com.jdc.restaurant.client;

import java.util.Map;

import com.jdc.restaurant.client.api.SummaryApi;
import com.jdc.restaurant.client.dto.SummaryDTO;
import com.jdc.restaurant.client.utils.RequestUtils;
import com.jdc.restaurant.client.utils.RestaurantClientFactory;

public class SummaryClient {

	private SummaryApi api;
	
	public SummaryClient() {
		api = RestaurantClientFactory.generate(SummaryApi.class);
	}
	
	public Map<String, Map<String, Integer>> getChartData(Map<String, String> query) {
		return RequestUtils.execute(api.getChartData(query));
	}
	
	public SummaryDTO getSummary() {
		return RequestUtils.execute(api.getSummary());
	}
}
