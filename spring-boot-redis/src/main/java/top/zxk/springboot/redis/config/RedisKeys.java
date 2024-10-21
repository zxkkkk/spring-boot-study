package top.zxk.springboot.redis.config;

public class RedisKeys {
    /**
     * 获取验证码 Key
     *
     * @param phone 手机号
     * @return {@link String} 验证码的 Redis Key
     */
    public static String getSmsKey(String phone) {
        return "sms:captcha:" + phone;
    }

    /**
     * 获取 accessToken Key
     *
     * @param accessToken 访问令牌
     * @return {@link String} accessToken 的 Redis Key
     */
    public static String getAccessTokenKey(String accessToken) {
        return "sys:access:" + accessToken;
    }

    /**
     * 获取用户 ID 的密钥
     *
     * @param id 用户 ID
     * @return {@link String} 用户 ID 的 Redis Key
     */
    public static String getUserIdKey(Long id) {
        return "sys:userId:" + id;
    }
}
