package com.mofof.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mofof.entity.mywork.CalEvent;
import com.alibaba.fastjson.JSONObject;
import com.mofof.entity.mywork.Alert;
import com.mofof.service.CalEventService;
import com.mofof.service.AlertService;
import com.mofof.util.DateUtil;
import com.mofof.util.GsonBuilderFactory;

@RestController
public class AlertController {

	@Autowired
	AlertService alertService;
	@Autowired
	CalEventService calEventService;
	
	@GetMapping("/cal_events/{id}/alerts")
	public String show(@PathVariable long id) {
		return GsonBuilderFactory.createGsonBuilder(
				Arrays.asList("calEvent","createTime")
			).toJson(alertService.findByCalEventId(id));
	}
	
	@PostMapping("/cal_events/{id}/alerts")
	public String create(@PathVariable long id,@RequestBody Alert alert) {
		Alert alert2 = alertService.findByCalEventId(id);
		if (alert2 == null) {
			CalEvent calEvent = calEventService.findById(id);
			alert.setCalEvent(calEvent);
			alert2 = alertService.save(alert);
		}
		return GsonBuilderFactory.createGsonBuilder(
				Arrays.asList("calEvent","createTime")
			).toJson(alert2);
	} 
}
