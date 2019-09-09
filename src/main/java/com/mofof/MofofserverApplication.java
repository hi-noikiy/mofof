package com.mofof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.quartz.AutowireCapableBeanJobFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mofof.config.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
  FileStorageProperties.class
})
public class MofofserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MofofserverApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    @Bean
    public SchedulerFactoryBean schedulerFactory(ApplicationContext applicationContext) {
      SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
      AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
      jobFactory.setApplicationContext(applicationContext);
      
      factoryBean.setJobFactory(jobFactory);
      factoryBean.setApplicationContextSchedulerContextKey("applicationContext");
      return factoryBean;
    }
}
