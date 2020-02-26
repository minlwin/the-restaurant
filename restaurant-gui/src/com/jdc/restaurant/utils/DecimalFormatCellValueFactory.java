package com.jdc.restaurant.utils;

import java.text.DecimalFormat;
import java.util.function.Function;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class DecimalFormatCellValueFactory<P>
		implements Callback<TableColumn.CellDataFeatures<P, String>, ObservableValue<String>> {

	private static final DecimalFormat DF = new DecimalFormat("#,##0");
	private Function<P, Integer> getter;

	public DecimalFormatCellValueFactory(Function<P, Integer> getter) {
		super();
		this.getter = getter;
	}

	@Override
	public ObservableValue<String> call(CellDataFeatures<P, String> param) {
		return new SimpleStringProperty(DF.format(getter.apply(param.getValue())));
	}
}
