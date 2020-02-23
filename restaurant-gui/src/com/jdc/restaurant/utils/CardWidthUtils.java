package com.jdc.restaurant.utils;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class CardWidthUtils {

	public static DoubleProperty getWidth(ReadOnlyDoubleProperty readOnlyDoubleProperty, Double minWidth, Double space) {
		
		DoubleProperty cardWidth = new SimpleDoubleProperty(minWidth);
		
		readOnlyDoubleProperty.addListener((a,b,c) -> {
			
			Double estimateCount = c.doubleValue() / (minWidth + space);
			int count = estimateCount.intValue();
			
			Double newWidth = c.doubleValue() - (space * count);
			
			cardWidth.set((newWidth + space - 12) / count);
		});
		
		return cardWidth;
	}
}
