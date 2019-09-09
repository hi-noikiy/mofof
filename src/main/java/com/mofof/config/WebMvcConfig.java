package com.mofof.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * mvc 配置类
 * Created by hzh on 2018/10/1.
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 进行跨域访问相关配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //可以跨域访问的URL路规则
                .allowCredentials(true)
                .allowedOrigins("*")  //可以跨域访问的访问者
                .allowedMethods("*") //可以跨域访问的方法
                .allowedHeaders("*")
        ;
    }

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }


}
