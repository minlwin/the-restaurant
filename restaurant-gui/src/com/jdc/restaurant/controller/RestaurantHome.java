package com.jdc.restaurant.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.SummaryDTO;
import com.jdc.restaurant.model.CategoryModel;
import com.jdc.restaurant.model.SummaryModel;
import com.jdc.restaurant.utils.AutoCompleteUtils;
import com.jdc.restaurant.utils.NumberFormatter;
import com.jdc.restaurant.utils.StringUtils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class RestaurantHome {

    @FXML
    private Label avairbleTables;

    @FXML
    private Label activeVouchers;

    @FXML
    private Label todaySales;

    @FXML
    private Label pendingOrders;

    @FXML
    private ComboBox<String> type;

    @FXML
    private TextField category;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private LineChart<String, Integer> chart;
    
    private ObjectProperty<Category> schCategory;
    
    private static ScheduledExecutorService schedule;
    
    @FXML
    private void initialize() {
    	
    	chart.getXAxis().setAnimated(false);
    	
    	schCategory = new SimpleObjectProperty<>();
    	type.getItems().addAll(CategoryModel.getModel().types());
    	
    	type.setOnKeyPressed(e -> {
    		if(e.getCode() == KeyCode.BACK_SPACE) {
    			type.setValue(null);
    		}
    	});
    	
    	AutoCompleteUtils.attach(category, this::searchCategory, schCategory::set);
    	
    	dateFrom.setValue(LocalDate.now().minusMonths(1));
    	
    	type.valueProperty().addListener((a,b,c) -> {
    		category.clear();
    		schCategory.set(null);
    		loadChart();
    	});
    	
    	schCategory.addListener((a,b,c) -> {
    		if(null != c) {
    			loadChart();
    		}
    	});
    	
    	category.textProperty().addListener((a,b,c) -> {
    		if(StringUtils.isEmpty(c)) {
        		schCategory.set(null);
    			loadChart();
    		}
    	});
    	
    	dateFrom.valueProperty().addListener((a,b,c) -> loadChart());
    	dateTo.valueProperty().addListener((a,b,c) -> loadChart());
    	
    	schedule = Executors.newScheduledThreadPool(1);
    	schedule.scheduleAtFixedRate(() -> {
    		
    		SummeryService service = new SummeryService();
    		service.setOnSucceeded(value -> {
    			SummaryDTO dto = service.getValue();
    			avairbleTables.setText(NumberFormatter.noMMKFormat(dto.getAvailableSeats()));
    			activeVouchers.setText(NumberFormatter.noMMKFormat(dto.getActiveVouchers()));
    			todaySales.setText(NumberFormatter.noMMKFormat(dto.getSaleToday()));
    			pendingOrders.setText(NumberFormatter.noMMKFormat(dto.getPendingOrders()));
    		});
    		
    		service.start();
    		
    	}, 0, 5, TimeUnit.SECONDS);
    	   	
    	loadChart();
    }
    
    private void loadChart() {
    	
    	chart.getData().clear();
    	
    	Map<String, Map<String, Integer>> data = SummaryModel.getModel().search(type.getValue(), schCategory.get(), dateFrom.getValue(), dateTo.getValue());
    	
    	for(String key : data.keySet()) {
    		
    		Series<String, Integer> series = new Series<String, Integer>();
    		series.setName(key);
    		
    		Map<String, Integer> map = data.get(key);
    		
    		for(String dataKey : map.keySet()) {
    			Data<String, Integer> chartData = new Data<>(dataKey, map.get(dataKey));
    			series.getData().add(chartData);
    		}
    		
    		chart.getData().add(series);
    	}
    	
    }
    
    private List<Category> searchCategory(String name) {
    	return CategoryModel.getModel().search(type.getValue(), category.getText());
    }
    
    private class SummeryService extends Service<SummaryDTO> {

		@Override
		protected Task<SummaryDTO> createTask() {
			return new Task<SummaryDTO>() {

				@Override
				protected SummaryDTO call() throws Exception {
					return SummaryModel.getModel().getSummary();
				}
			};
		}
    	
    }
    
    public static void stopService() {
    	if(null != schedule && !schedule.isShutdown()) {
    		schedule.shutdown();
    		schedule = null;
    	}
    }

}
