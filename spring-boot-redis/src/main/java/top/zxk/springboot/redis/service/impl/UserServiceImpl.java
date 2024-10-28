package top.zxk.springboot.redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import top.zxk.springboot.redis.cache.TokenStoreCache;
import top.zxk.springboot.redis.config.RedisCache;
import top.zxk.springboot.redis.config.RedisKeys;
import top.zxk.springboot.redis.entity.User;
import top.zxk.springboot.redis.enums.AccountStatusEnum;
import top.zxk.springboot.redis.enums.ErrorCode;
import top.zxk.springboot.redis.exception.ServerException;
import top.zxk.springboot.redis.mapper.UserMapper;
import top.zxk.springboot.redis.service.UserService;
import top.zxk.springboot.redis.utils.JwtUtil;
import top.zxk.springboot.redis.vo.UserInfoVO;
import top.zxk.springboot.redis.vo.UserLoginVO;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final RedisCache redisCache;
    private final TokenStoreCache tokenStoreCache;

    @Override
    public UserLoginVO loginByPhone(String phone, String code) {
        // 获取验证码的缓存 Key
        String smsCacheKey = RedisKeys.getSmsKey(phone);

        // 从 Redis 中获取验证码
        Integer redisCode = (Integer) redisCache.get(smsCacheKey);

        // 校验验证码合法性
        if (ObjectUtils.isEmpty(redisCode) || !redisCode.toString().equals(code)) {
            throw new ServerException(ErrorCode.SMS_CODE_ERROR);
        }

        // 删除已使用的验证码
        redisCache.delete(smsCacheKey);

        // 根据手机号获取用户
        User user = baseMapper.getByPhone(phone);

        // 判断用户是否注册过，如果用户为空代表未注册，进行注册。否则开启登录流程
        if (ObjectUtils.isEmpty(user)) {
            log.info("用户不存在，创建用户, phone: {}", phone);
            user = new User();
            user.setNickname(phone);
            user.setPhone(phone);
            user.setAvatar("https://tse4-mm.cn.bing.net/th/id/OIP-C.7GLMYPqMlt2LgkbPsOnDIAAAAA?rs=1&pid=ImgDetMain");
            user.setGender(0);
            user.setEnabled(AccountStatusEnum.ENABLED.getValue());
            user.setBonus(100);
            user.setDeleteFlag(0);
            user.setRemark("这个人很懒，什么都没有写");
            baseMapper.insert(user);
        }

        // 用户被禁用
        if (!user.getEnabled().equals(AccountStatusEnum.ENABLED.getValue())) {
            throw new ServerException(ErrorCode.USER_DISABLED);
        }

        // 生成 JWT token
        String token = JwtUtil.createToken(user.getPkId());

        // 保存用户登录信息到 Redis
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setPkId(user.getPkId());
        userLoginVO.setPhone(user.getPhone());
        userLoginVO.setAccessToken(token);
        tokenStoreCache.saveUser(token, userLoginVO);

        return userLoginVO;
    }

    @Override
    public boolean checkUserEnabled(Long userId) {
        User user = baseMapper.selectById(userId);
        if (ObjectUtils.isEmpty(user)) {
            return false;
        }
        return user.getEnabled().equals(AccountStatusEnum.ENABLED.getValue());
    }

    @Override
    public UserInfoVO userInfo(Long userId) {
        // 查询数据库
        User user = baseMapper.selectById(userId);
        if (user == null) {
            log.error("用户不存在, userId: {}", userId);
            throw new ServerException(ErrorCode.USER_NOT_EXIST);
        }
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(user, userInfoVO);
        return userInfoVO;
    }
}
