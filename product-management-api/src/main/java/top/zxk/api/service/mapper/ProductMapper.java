package top.zxk.api.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zxk.api.entity.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
