package top.gloryjie.learn.spring.config;

import org.springframework.boot.ResourceBanner;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import top.gloryjie.learn.spring.filter.CustomFilter;
import top.gloryjie.learn.spring.interceptor.CustomInterceptor;

/**
 * webMVC配置，可以看到
 * @author Jie
 * @since 2020/8/2
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor());
    }


    @Bean
    public FilterRegistrationBean<CustomFilter> myCustomFilter(){
        FilterRegistrationBean<CustomFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(customFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean
    public CustomFilter customFilter(){
        return new CustomFilter();
    }
}
