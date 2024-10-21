package top.zxk.springboot.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zxk.springboot.redis.entity.User;
import top.zxk.springboot.redis.vo.UserInfoVO;
import top.zxk.springboot.redis.vo.UserLoginVO;

public interface UserService extends IService<User> {

    /**
     * 根据手机号和验证码进行登录
     *
     * @param phone 手机号
     * @param code 验证码
     * @return {@link UserLoginVO} 用户登录信息
     */
    UserLoginVO loginByPhone(String phone, String code);

    boolean checkUserEnabled(Long userId);

    UserInfoVO userInfo(Long userId);
}
