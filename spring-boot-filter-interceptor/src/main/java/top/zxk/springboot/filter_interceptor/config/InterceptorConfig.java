package top.zxk.springboot.filter_interceptor.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.zxk.springboot.filter_interceptor.interceptor.AuthInterceptor;
import top.zxk.springboot.filter_interceptor.interceptor.CorsInterceptor;
import top.zxk.springboot.filter_interceptor.interceptor.LoggingInterceptor;
import top.zxk.springboot.filter_interceptor.interceptor.UploadInterceptor;

@Configuration
@AllArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final LoggingInterceptor loggingInterceptor;
    private final AuthInterceptor authInterceptor;
    private final CorsInterceptor corsInterceptor;
    private final UploadInterceptor uploadInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/**");
        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
        registry.addInterceptor(uploadInterceptor).addPathPatterns("/**");
    }
}
