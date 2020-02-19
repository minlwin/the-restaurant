package com.jdc.restaurant.utils;

import javafx.scene.shape.SVGPath;

public enum Icons {

	EDIT("M12.3 3.7l4 4-12.3 12.3h-4v-4l12.3-12.3zM13.7 2.3l2.3-2.3 4 4-2.3 2.3-4-4zM12.3 3.7l4 4-12.3 12.3h-4v-4l12.3-12.3zM13.7 2.3l2.3-2.3 4 4-2.3 2.3-4-4z");
	
	private String content;

	private Icons(String content) {
		this.content = content;
	}
	
	public SVGPath getSvg() {
		SVGPath path = new SVGPath();
		path.setContent(content);
		return path;
	}

}
