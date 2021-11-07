package com.ms.blogserver.config.shiro;

import com.ms.blogserver.config.jwt.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: shiro配置
 * @author: zhh
 * @time: 2021/5/24
 */

@Configuration
public class ShiroConfig {
    /**
     * 先经过token过滤器，如果检测到请求头存在 token，则用 token 去 login，接着走 Realm 去验证
     */
    @Bean
    public ShiroFilterFactoryBean factory(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        Map<String, Filter> filterMap=new LinkedHashMap<>();
        filterMap.put("jwt",jwtFilterBean());
        factoryBean.setSecurityManager(securityManager);
        Map<String, String> filterRuleMap = new LinkedHashMap<>();
        // 放行不需要权限认证的接口
        //放行Swagger接口
        filterRuleMap.put("/v2/api-docs","anon");
        filterRuleMap.put("/swagger-resources/configuration/ui","anon");
        filterRuleMap.put("/swagger-resources","anon");
        filterRuleMap.put("/swagger-resources/configuration/security","anon");
        filterRuleMap.put("/swagger-ui.html/**","anon");
        filterRuleMap.put("/webjars/**","anon");
        //放行登录接口和其他不需要权限的接口
        filterRuleMap.put("/log/**","anon");
        filterRuleMap.put("/filter/error","anon");
        filterRuleMap.put("/login", "anon");
        filterRuleMap.put("/logout", "anon");
        filterRuleMap.put("/code/**","anon");
        filterRuleMap.put("/article/**","anon");
        filterRuleMap.put("/user/**","anon");
        filterRuleMap.put("/client/**","anon");
        // 所有请求通过JWT Filter
        filterRuleMap.put("/**", "jwt");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        factoryBean.setFilters(filterMap);
        return factoryBean;

    }

    /**
     * 注入 securityManager
     */
    @Bean
    public SecurityManager securityManager(CustomRealm customRealm) {
        //设置自定义Realm
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        //关闭shiro自带的session
        DefaultSubjectDAO subjectDAO=new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator=new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    /**
     * jwtFilter不能交由spring容器管理，否则shiro中配置的anon过滤无效
     *
     * @return
     */
    public JwtFilter jwtFilterBean() {
        return new JwtFilter();
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


}
