package com.jdc.restaurant.utils;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class CardWidthUtils {

	public static DoubleProperty getWidth(ReadOnlyDoubleProperty readOnlyDoubleProperty, Double minWidth, Double space) {
		
		double calWidth = getWidth(readOnlyDoubleProperty.get(), minWidth, space);
		
		DoubleProperty cardWidth = new SimpleDoubleProperty(calWidth);
		
		readOnlyDoubleProperty.addListener((a,b,c) -> {
			cardWidth.set(getWidth(c.doubleValue(), minWidth, space));
		});
		
		return cardWidth;
	}
	
	private static double getWidth(Double container, Double minWidth, Double space) {
		Double estimateCount = container / (minWidth + space);
		int count = estimateCount.intValue();
		
		Double newWidth = container - (space * count);
		
		return (newWidth + space - 12) / count;
	}
}
