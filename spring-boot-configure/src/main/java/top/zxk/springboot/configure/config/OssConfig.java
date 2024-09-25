package top.zxk.springboot.configure.config;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
//@ConfigurationProperties(prefix = "aliyun-oss")
//@PropertySource("file:D:/code/spring-boot-practice/spring-boot-configure/src/main/resources/aliyun-oss.properties")
public class OssConfig {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
//
//    @Value("${aliyun-oss.dir}")
//    private String dir;

//    @Value("${aliyun-oss.host}")
//    private String host;

//    @PostConstruct
//    public void init() {
//        System.out.println("Endpoint: " + endpoint);
//        System.out.println("Bucket: " + bucket);
//    }
    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
