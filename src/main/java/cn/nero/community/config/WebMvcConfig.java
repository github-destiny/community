package cn.nero.community.config;

import cn.nero.community.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/18
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new MyInterceptor())
        //        .addPathPatterns("/**");
    }
}
