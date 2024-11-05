package top.zxk.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zxk.api.entity.Product;
import top.zxk.api.service.mapper.ProductMapper;
import top.zxk.api.service.ProuctService;


@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProuctService {
}
