package top.zxk.springboot.mp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.zxk.springboot.mp.entity.Course;
import top.zxk.springboot.mp.entity.Student;
import top.zxk.springboot.mp.mapper.StudentCourseMapper;
import top.zxk.springboot.mp.mapper.StudentMapper;

import java.util.List;


@Service
@AllArgsConstructor
public class StudentService {
    private final StudentMapper studentMapper;
    private final StudentCourseMapper studentCourseMapper;
    /**
     * 根据学⽣ID获取该学⽣的课程信息
     */
    public Student getStudentWithCourses(Long studentId) {
        Student student = studentMapper.selectById(studentId);
        if (student != null) {
            List<Course> courses = studentCourseMapper.selectCoursesByStudentId(studentId);
            student.setCourses(courses);
        }
        return student;
    }
}
