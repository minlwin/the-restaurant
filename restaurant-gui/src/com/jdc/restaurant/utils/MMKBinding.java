package com.jdc.restaurant.utils;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;

public class MMKBinding extends StringBinding{
		
	private IntegerProperty prop;
	
	public MMKBinding(IntegerProperty binding) {
		bind(binding);
		this.prop = binding;
	}

	@Override
	protected String computeValue() {
		return NumberFormatter.formatMMK(prop.intValue());
	}

}
