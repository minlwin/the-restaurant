package com.jdc.restaurant.utils;

import com.jdc.restaurant.RestaurantAppException;

public class ValidationUtils {

	public static<T> void notNull(T data, Class<T> type) {
		if(null == data) {
			throw new RestaurantAppException(String.format("%s must not be null!", type.getSimpleName()));
		}
	}
	
	public static <T> void notNullSelect(T data, Class<T> type) {
		if(null == data) {
			throw new RestaurantAppException(String.format("Please select %s!", type.getSimpleName()));
		}
	}

	public static void notEmptyStringInput(String data, String field) {
		
		if(StringUtils.isEmpty(data)) {
			throw new RestaurantAppException(String.format("Please enter %s!", field));
		}
	}
	
	public static void notEmptyStringSelect(String data, String field) {
		
		if(StringUtils.isEmpty(data)) {
			throw new RestaurantAppException(String.format("Please select %s!", field));
		}
	}

	public static<T extends Number> void notZeroNumberInputs(T data, String field) {
		
		if(data.intValue() == 0) {
			throw new RestaurantAppException(String.format("Please enter %s!", field));
		}
	}
	
	
}
