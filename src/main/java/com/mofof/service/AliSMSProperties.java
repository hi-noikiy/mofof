package com.mofof.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ali-sms")
public class AliSMSProperties {
  private String accessKey;
  private String secret;

  private String signName;
  private String templateName;

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String uploadDir) {
    this.accessKey = uploadDir;
  }
}