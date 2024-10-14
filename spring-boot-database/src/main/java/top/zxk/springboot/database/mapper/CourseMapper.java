package top.zxk.springboot.database.mapper;

import top.zxk.springboot.database.entity.Course;

import java.util.List;

public interface CourseMapper {

    List<Course> selectAll();
}
