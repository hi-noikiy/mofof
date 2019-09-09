package com.mofof.controller;

import java.util.Arrays;
import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class APIController {
	
	protected List<String> gsonIgnoreFields() {
		return Arrays.asList();
	}
	
	protected Gson gson() {
		Gson gson = new GsonBuilder()
	            .addSerializationExclusionStrategy(new ExclusionStrategy() {
	                @Override
	                public boolean shouldSkipField(FieldAttributes f) {
	                	String fname = f.getName();
	                    return gsonIgnoreFields().contains(fname);
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
