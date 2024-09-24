package top.zxk.zhihu.api.mapper;

import top.zxk.zhihu.api.entity.Special;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SpecialMapper {
    List<Special> selectAll();
}
