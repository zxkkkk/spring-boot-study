package top.zxk.springboot.database.mapper;

import top.zxk.springboot.database.entity.Teacher;

public interface TeacherMapper {
    Teacher findTeacherById(int teacherId);
}