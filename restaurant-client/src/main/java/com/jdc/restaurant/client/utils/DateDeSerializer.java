package com.jdc.restaurant.client.utils;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateDeSerializer extends JsonDeserializer<Date>{

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		
		try {
			if(p.getCurrentTokenId() == JsonTokenId.ID_STRING) {
				return DateFormatForJson.df.parse(p.getText());
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
