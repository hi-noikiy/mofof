package com.mofof.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * app全局自定义配置类
 * Created by hzh on 2018/10/1.
 */
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private String baseUploadFilePath;
    private String downloadPath;
    private String image;


    public String getBaseUploadFilePath() {
        return baseUploadFilePath;
    }

    public void setBaseUploadFilePath(String baseUploadFilePath) {
        this.baseUploadFilePath = baseUploadFilePath;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

}
