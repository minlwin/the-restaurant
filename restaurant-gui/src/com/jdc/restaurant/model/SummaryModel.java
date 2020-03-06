package com.jdc.restaurant.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.jdc.restaurant.client.SummaryClient;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.SummaryDTO;

public class SummaryModel {
	
	private static SummaryModel model;
	
	private SummaryClient client;
	
	public static SummaryModel getModel() {
		
		if(null == model) {
			model = new SummaryModel();
		}
		
		return model;
	}
	
	private SummaryModel() {
		this.client = new SummaryClient();
	}

	public Map<String, Map<String, Integer>> search(String type, Category category, LocalDate from, LocalDate to) {
		
		Map<String, String> query = new HashMap<>();
		
		if(null != type) {
			query.put("type", type);
		}
		
		if(null != category) {
			query.put("category", String.valueOf(category.getId()));
		}
		
		if(null != from) {
			query.put("from", from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		
		if(null != to) {
			query.put("to", to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		
		return client.getChartData(query);
	}

	public SummaryDTO getSummary() {
		return client.getSummary();
	}
	
}
