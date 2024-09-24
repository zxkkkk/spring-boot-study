package top.zxk.springboot.configure.entity;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("person")
class PersonTest {
    @Resource
    private Person person;

    @Test
    void testPerson() {
        System.out.println(person);
    }

}