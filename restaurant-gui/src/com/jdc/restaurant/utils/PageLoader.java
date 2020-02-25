package com.jdc.restaurant.utils;

import javafx.scene.Parent;

public interface PageLoader {

	void loadView(Parent view);
	
	void loadView(Page page);
}
