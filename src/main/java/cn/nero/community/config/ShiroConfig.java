package cn.nero.community.config;

import cn.nero.community.realms.AdminRealm;
import cn.nero.community.realms.CustomerModularRealmAuthenticator;
import cn.nero.community.realms.UserRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/6
 */
@Configuration
@Slf4j
public class ShiroConfig {

    // 解决shiro加入后访问API404的问题
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }

    // 获取一个ShiroFilter,拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(manager);
        Map<String, String> map = new HashMap<>();
        map.put("/**", "anon");
        map.put("/to*", "anon");
        map.put("/admin/login", "anon");
        map.put("/admin/register", "anon");
        shiroFilter.setFilterChainDefinitionMap(map);
        shiroFilter.setLoginUrl("/");
        log.info("shiro filter已经生效....");
        return shiroFilter;
    }

    @Bean
    public ModularRealmAuthenticator getModularRealmAuthenticator(){
        CustomerModularRealmAuthenticator customerModularRealmAuthenticator = new CustomerModularRealmAuthenticator();
        customerModularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return customerModularRealmAuthenticator;
    }
    
    // 获取安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setAuthenticator(getModularRealmAuthenticator());
        List<Realm> realms = new ArrayList<>();
        realms.add(getUserRealm());
        realms.add(getAdminRealm());
        manager.setRealms(realms);
        return manager;
    }

    @Bean
    public HashedCredentialsMatcher getHashedCredentialMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashIterations(1024);
        matcher.setHashAlgorithmName("MD5");
        return matcher;
    }

    @Bean
    public UserRealm getUserRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(getHashedCredentialMatcher());
        return userRealm;
    }

    @Bean
    public AdminRealm getAdminRealm(){
        AdminRealm adminRealm = new AdminRealm();
        adminRealm.setCredentialsMatcher(getHashedCredentialMatcher());
        return adminRealm;
    }
    

}
