package top.zxk.zhihu.api.controller;


import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zxk.zhihu.api.common.ResponseResult;
import top.zxk.zhihu.api.entity.Special;
import top.zxk.zhihu.api.service.SpecialService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/specials/*")
public class SpecialController {
    @Resource
    private SpecialService specialService;

    @RequestMapping("/all")
    public ResponseResult getAll(){
        List<Special> all = specialService.getAll();
        return ResponseResult.builder()
                .code(String.valueOf(200))
                .msg("数据获取成功")
                .data(all)
                .build();
    }
}
