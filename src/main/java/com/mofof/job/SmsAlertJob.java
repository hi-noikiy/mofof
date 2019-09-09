package com.mofof.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsAlertJob implements Job {
  
  Logger log = LoggerFactory.getLogger(SmsAlertJob.class);
  
  public SmsAlertJob() {  
  }
  
  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    log.info("Job triggered to send sms");
    JobDataMap map = context.getMergedJobDataMap();
    log.info("===>" + map.get("title"));
    log.info("Job completed");
    System.out.println("context.getJobDetail().getKey()=" + context.getJobDetail().getKey());
  }

}
