package com.mofof.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mofof.shiro.MoFoFRealm;

@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        return new MoFoFRealm();
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        creator.setUsePrefix(true);
        return creator;
    }

    /**
     * @see org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @return
     */
    /*
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login");
        bean.setUnauthorizedUrl("/unauthor");
        
        Map<String, Filter>filters = Maps.newHashMap();
        filters.put("perms", corsPermFilter());
        filters.put("anon", new AnonymousFilter());
        bean.setFilters(filters);
        
        Map<String, String> chains = Maps.newHashMap();
        chains.put("/login", "anon");
        chains.put("/unauthor", "anon");
        chains.put("/logout", "logout");
        chains.put("/base/**", "anon");
        chains.put("/css/**", "anon");
        chains.put("/layer/**", "anon");

        chains.put("/**", "perms");
        bean.setFilterChainDefinitionMap(chains);
        return bean;
    }
    
    @Bean
    public CORSPermissionAuthorizationFilter corsPermFilter() {
        return new CORSPermissionAuthorizationFilter();
    }
    */
    
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();

        chain.addPathDefinition("/user/login", "anon");
        chain.addPathDefinition("/page/401", "anon");
        chain.addPathDefinition("/page/403", "anon");
        chain.addPathDefinition("/user/logout", "anon");

        // chain.addPathDefinition("/t5/hello", "anon");
        // chain.addPathDefinition("/t5/guest", "anon");

        chain.addPathDefinition("/**", "authc");
//        chain.addPathDefinition("/**", "anon");
        return chain;
        
    }

}