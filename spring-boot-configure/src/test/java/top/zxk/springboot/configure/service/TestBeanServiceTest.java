package top.zxk.springboot.configure.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TestBeanServiceTest {

    @Resource
    private ConfigurableApplicationContext ioc;

    @Test
    void test(){
        TestBeanService testBeanService = (TestBeanService) ioc.getBean("myTestBeanService");
        System.out.println(testBeanService.getName());
    }
}