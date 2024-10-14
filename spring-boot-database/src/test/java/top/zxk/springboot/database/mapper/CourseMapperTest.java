package top.zxk.springboot.database.mapper;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.zxk.springboot.database.entity.Course;
import top.zxk.springboot.database.entity.Student;

import java.util.List;

@SpringBootTest
@Slf4j
class CourseMapperTest {
    @Resource
    private CourseMapper courseMapper;
    @Test
    void selectAll() {
        List<Course> courses = courseMapper.selectAll();
        courses.forEach(course -> {
            log.info("{}", course.getCourseName());
            List<Student> students = course.getStudents();
            students.forEach(student -> log.info("{},{}", student.getStudentName(), student.getHometown()));
        });
    }
}