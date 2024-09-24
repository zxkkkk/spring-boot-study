package top.zxk.zhihu.api.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.zxk.zhihu.api.entity.Special;
import top.zxk.zhihu.api.mapper.SpecialMapper;
import top.zxk.zhihu.api.service.SpecialService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class SpecialServiceImpl implements SpecialService {
    @Resource
    public SpecialMapper specialMapper;

    @Override
    public List<Special> getAll() {
        List<Special> specials = specialMapper.selectAll();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        specials.forEach(special -> {
            String format = df.format(new Date(Long.parseLong(special.getUpdated() + "000")));
            special.setUpdated(format);
        });
        return specials;
    }
}