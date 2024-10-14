package top.zxk.springboot.database.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zxk.springboot.database.entity.Student;

import java.util.List;

@Mapper
public interface StudentMapper {
    int insert(Student student);
    Student findStudentById(int studentId);
    int updateById(Student student);
    int deleteById(int studentId);
    int batchInsert(@Param("students") List<Student> students);
    int batchDelete(@Param("idList") List<Integer> ids);
    int batchUpdate(@Param("students") List<Student> students);
    List<Student> selectByDynamicSql(Student student);
    Student getStudent(int studentId);
}
