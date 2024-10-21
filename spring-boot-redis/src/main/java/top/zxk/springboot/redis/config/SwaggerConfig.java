package top.zxk.springboot.redis.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("zxk 的 API")
                        .contact(new Contact().name("zxk").email("858654746@qq.com"))
                        .version("1.0")
                        .description("zxk 的 API 接⼝⽂档")
                        .license(new License().name("Apache 2.0").url("http://doc.xiaominfo.com")));
    }
}