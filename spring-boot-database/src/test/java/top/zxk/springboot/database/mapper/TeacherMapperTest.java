package top.zxk.springboot.database.mapper;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.zxk.springboot.database.entity.Teacher;

@SpringBootTest
@Slf4j
class TeacherMapperTest {
    @Resource
    private TeacherMapper teacherMapper;

    @Test
    void findTeacherById() {
        Teacher teacher = teacherMapper.findTeacherById(1);
        log.info(teacher.getTeacherName());
        log.info(teacher.getClazz().getClazzName());
    }
}
