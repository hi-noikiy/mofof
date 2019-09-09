package com.mofof.service;


import static java.time.ZoneId.systemDefault;
import static org.quartz.JobKey.jobKey;
import static org.quartz.TriggerKey.triggerKey;

import java.sql.Date;
import java.util.Objects;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mofof.entity.mywork.Alert;
import com.mofof.repository.AlertRepository;


@Service
public class AlertService {
  Logger log = LoggerFactory.getLogger(AlertService.class);
  
  @Autowired
  private Scheduler scheduler;
	@Autowired
	AlertRepository alertRepository;
	
	public Alert findByCalEventId(long id) {
		return alertRepository.findByCalEventId(id);
	}
	/**
	 * 
	 * @param alert
	 * @return
	 */
	public Alert save(Alert alert) {
    Alert alert2 = alertRepository.save(alert);
    JobDetail jobDetail = alert.buildJobDetail();
    Trigger trigger = alert2.buildTrigger();
//    log.info("About to save job with key - {}", jobDetail.getKey());
    try {
      scheduler.scheduleJob(jobDetail, trigger);
      // log.info("Job with key - {} saved sucessfully", jobDetail.getKey());
    } catch (SchedulerException e) {
      // log.error("Could not save job with key - {} due to error - {}",
      // jobDetail.getKey(), e.getLocalizedMessage());
      throw new IllegalArgumentException(e.getLocalizedMessage());
    }
    return alert2;
	}
	
	public void update(Alert alert) {
	  String notifyVia = alertRepository.findOne(alert.getId()).getNotifyVia();
	  Alert alert2 = alertRepository.save(alert);
	  try {
      JobDetail oldJobDetail = scheduler.getJobDetail(jobKey(alert.buildName(),notifyVia));
      Trigger oldTrigger = scheduler.getTrigger(triggerKey(alert.buildName(),notifyVia));
      
      if(Objects.nonNull(oldJobDetail)) {
        JobDataMap jobDataMap = oldJobDetail.getJobDataMap();
        jobDataMap.put("title", alert2.getCalEvent().getTitle());
        jobDataMap.put("location", alert2.getCalEvent().getLocation());
        jobDataMap.put("time", alert2.getCalEvent().getStartedAt());
        JobBuilder jb = oldJobDetail.getJobBuilder();
        JobDetail newJobDetail = jb.usingJobData(jobDataMap).storeDurably().build();
        scheduler.addJob(newJobDetail, true);
        log.info("Updated job with key - {}", newJobDetail.getKey());
        
      }
      if(Objects.nonNull(oldTrigger)) {
        TriggerBuilder tb = oldTrigger.getTriggerBuilder();
        Trigger newTrigger = tb.startAt(Date.from(alert2.getTriggedAt().atZone(systemDefault()).toInstant())).build();
        scheduler.rescheduleJob(triggerKey(alert.buildName(),notifyVia), newTrigger);      
        return;
      }
      log.warn("Could not find job with key - {}.{} to update");
    } catch (SchedulerException e) {
      log.error("Could not find job with key - {}.{} to update due to error - {}");
    }
	}
	@Modifying
	@Transactional
	public void deleteByCalEventId(long id) {
		alertRepository.deleteByCalEventId(id);
	}

  public void deleteById(Alert alert) {
    try {      
      scheduler.pauseTrigger(triggerKey(alert.buildName(), alert.getNotifyVia()));
      scheduler.unscheduleJob(triggerKey(alert.buildName(), alert.getNotifyVia()));
      scheduler.deleteJob(jobKey(alert.buildName(), alert.getNotifyVia()));
      alertRepository.delete(alert.getId());
    } catch (SchedulerException e) {
      e.printStackTrace();
    }

  }
  public Alert findById(Long id) {
    return alertRepository.findOne(id);
    
  }
}
