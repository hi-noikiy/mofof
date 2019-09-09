package com.mofof.job;

import java.time.LocalDateTime;
import static java.util.UUID.randomUUID;
import org.quartz.Trigger;
import static org.springframework.util.StringUtils.isEmpty;

public class TriggerDescriptor {

  private String name;
  private String group;
  private LocalDateTime fireTime;
  private String cron;

  public TriggerDescriptor setName(String name) {
    this.name = name;
    return this;
  }

  public TriggerDescriptor setGroup(String group) {
    this.group = group;
    return this;
  }

  public TriggerDescriptor setFireTime(LocalDateTime fireTime) {
    this.fireTime = fireTime;
    return this;
  }

  public TriggerDescriptor setCron(String cron) {
    this.cron = cron;
    return this;
  }

  private String buildName() {
    return isEmpty(name) ? randomUUID().toString() : name;
  }

  /**
   * 
   * @param trigger
   *            the Trigger used to build this descriptor
   * @return the TriggerDescriptor
   */
  public static TriggerDescriptor buildDescriptor(Trigger trigger) {
    // @formatter:off
    return new TriggerDescriptor()
        .setName(trigger.getKey().getName())
        .setGroup(trigger.getKey().getGroup())
      //  .setFireTime((LocalDateTime) trigger.getJobDataMap().get("fireTime"))
        .setCron("0/30 * * * * ?");
    // @formatter:on
  }
}
