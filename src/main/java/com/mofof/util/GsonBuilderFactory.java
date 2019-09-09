package com.mofof.util;

import java.time.LocalDateTime;
import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonBuilderFactory {
	public static Gson createGsonBuilder(List<String> ignores) {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.serializeNulls()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonUtil())
	            .addSerializationExclusionStrategy(new ExclusionStrategy() {
	                @Override
	                public boolean shouldSkipField(FieldAttributes f) {
	                	String fname = f.getName();
	                    return ignores.contains(fname);
	                }
	
	                @Override
	                public boolean shouldSkipClass(Class<?> aClass) {
	                    return false;
	                }
	            })	         
	            .create();
		return gson;
	}
}
