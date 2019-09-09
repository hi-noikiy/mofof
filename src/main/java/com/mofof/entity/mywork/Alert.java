package com.mofof.entity.mywork;

import static java.time.ZoneId.systemDefault;
import static java.util.UUID.randomUUID;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.springframework.util.StringUtils.isEmpty;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mofof.entity.common.BaseEntity;
import com.mofof.job.SmsAlertJob;
import com.mofof.job.TriggerDescriptor;

@Entity
@SQLDelete(sql = "update alert set del = 1 where id = ?")
@SQLDeleteAll(sql = "update alert set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Alert extends BaseEntity {

	private String notifyVia;		  //提醒方式
	private int nMinBefore;  // 提前N分钟提醒
	@ColumnDefault("1")
	private int repeatCount;  // 重复次数
	private LocalDateTime triggedAt;//提醒时间
	private LocalDateTime firedAt; // (已经)触发时间
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "calEventId")	
	private CalEvent calEvent;	
	@Transient
  private Map<String, Object> data = new LinkedHashMap<>();
	
	@Transient
	@JsonProperty("triggers")
  private List<TriggerDescriptor> triggerDescriptors = new ArrayList<>();

	public String buildName() {
	  return "alert_" + getId();
	}
	
  public JobDetail buildJobDetail() {
    // @formatter:off
    JobDataMap jobDataMap = new JobDataMap(getData());
    jobDataMap.put("title", this.calEvent.getTitle());
    jobDataMap.put("location", this.calEvent.getLocation());
    jobDataMap.put("time", this.calEvent.getStartedAt());
    return newJob(SmsAlertJob.class)
                .withIdentity(this.buildName(),this.notifyVia)
                .usingJobData(jobDataMap)
                .build();
    // @formatter:on
  }

  public Trigger buildTrigger() {
    // @formatter:off
    if (!isEmpty(triggedAt)) {
      JobDataMap jobDataMap = new JobDataMap();
      jobDataMap.put("triggedAt", triggedAt);
      return newTrigger()
          .withIdentity(this.buildName(), this.notifyVia)
          .withSchedule(simpleSchedule()
              .withMisfireHandlingInstructionNextWithExistingCount())
          .startAt(Date.from(triggedAt.atZone(systemDefault()).toInstant()))
          .usingJobData(jobDataMap)
          .build();
    }
    // @formatter:on
    throw new IllegalStateException("unsupported trigger descriptor " + this);
  }

	public String getNotifyVia() {
    return notifyVia;
  }

  public void setNotifyVia(String notifyVia) {
    this.notifyVia = notifyVia;
  }

  public LocalDateTime getTriggedAt() {
		return triggedAt;
	}

	public void setTriggedAt(LocalDateTime triggedAt) {
		this.triggedAt = triggedAt;
	}

  public CalEvent getCalEvent() {
    return calEvent;
  }

  public void setCalEvent(CalEvent calEvent) {
    this.calEvent = calEvent;
  }

  public Map<String, Object> getData() {
    return data;
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }

  public int getnMinBefore() {
    return nMinBefore;
  }

  public void setnMinBefore(int nMinBefore) {
    this.nMinBefore = nMinBefore;
  }

  public int getRepeatCount() {
    return repeatCount;
  }

  public void setRepeatCount(int repeatCount) {
    this.repeatCount = repeatCount;
  }

  public LocalDateTime getFiredAt() {
    return firedAt;
  }

  public void setFiredAt(LocalDateTime firedAt) {
    this.firedAt = firedAt;
  }
}
