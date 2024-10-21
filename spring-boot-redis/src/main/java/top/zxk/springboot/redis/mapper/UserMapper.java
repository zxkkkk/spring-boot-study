package top.zxk.springboot.redis.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.zxk.springboot.redis.entity.User;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据手机号获取用户
     *
     * @param phone 手机号
     * @return 用户实体对象
     */
    default User getByPhone(String phone) {
        return this.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
    }
}
