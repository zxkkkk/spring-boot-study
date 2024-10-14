package top.zxk.springboot.database.mapper;

import top.zxk.springboot.database.entity.Special;

import java.util.List;

public interface SpecialMapper {
    List<Special> findAll();
}
