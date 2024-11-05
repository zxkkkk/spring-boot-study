package top.zxk.springboot.filter_interceptor.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.zxk.springboot.filter_interceptor.filter.AuthFilter;
import top.zxk.springboot.filter_interceptor.filter.CorsFilter;
import top.zxk.springboot.filter_interceptor.filter.LoggingFilter;
import top.zxk.springboot.filter_interceptor.filter.RateLimitFilter;

@Configuration
public class FilterConfig {
    //    @Bean
//    public FilterRegistrationBean<CustomFilter> filterRegistrationBean(){
//        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new CustomFilter());
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }
    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilter(){
        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoggingFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
//    @Bean
//    public FilterRegistrationBean<AuthFilter> authFilter(){
//        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new AuthFilter());
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(2);
//        return registrationBean;
//    }
//    @Bean
//    public FilterRegistrationBean<CorsFilter> authFilter(){
//        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new CorsFilter());
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(3);
//        return registrationBean;
//    }
        @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilter(){
        FilterRegistrationBean<RateLimitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RateLimitFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(3);
        return registrationBean;
    }
}