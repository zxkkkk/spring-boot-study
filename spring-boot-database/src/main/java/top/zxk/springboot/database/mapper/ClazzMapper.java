package top.zxk.springboot.database.mapper;


import top.zxk.springboot.database.entity.Clazz;

public interface ClazzMapper {
    Clazz getClazzById(int clazzId);
    Clazz getClazz(int clazzId);
}