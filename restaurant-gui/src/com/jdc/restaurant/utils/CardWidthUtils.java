package com.jdc.restaurant.utils;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class CardWidthUtils {

	public static DoubleProperty getWidth(ReadOnlyDoubleProperty readOnlyDoubleProperty, Double minWidth, Double space) {
		
		DoubleProperty cardWidth = getWidth(readOnlyDoubleProperty.get(), minWidth, space);
		
		readOnlyDoubleProperty.addListener((a,b,c) -> {
			cardWidth.set(getWidth(c.doubleValue(), minWidth, space).get());
		});
		
		return cardWidth;
	}
	
	private static DoubleProperty getWidth(Double container, Double minWidth, Double space) {
		DoubleProperty cardWidth = new SimpleDoubleProperty();
		Double estimateCount = container / (minWidth + space);
		int count = estimateCount.intValue();
		
		Double newWidth = container - (space * count);
		
		cardWidth.set((newWidth + space - 12) / count);
		return cardWidth;
	}
}
