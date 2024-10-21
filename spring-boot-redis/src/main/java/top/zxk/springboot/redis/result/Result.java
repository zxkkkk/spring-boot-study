package top.zxk.springboot.redis.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用响应结果类
 *
 * @param <T> 数据类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private T data;        // 存储响应的数据
    private String message; // 存储响应消息
    private int code;      // 存储状态码

    /**
     * 成功返回结果
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return Result<T> 返回成功结果
     */
    public static <T> Result<T> ok(T data) {
        return new Result<>(data, "成功", 200); // 200 为成功的状态码
    }

    /**
     * 错误返回结果
     *
     * @param message 错误信息
     * @param code    状态码
     * @param <T>     数据类型
     * @return Result<T> 返回错误结果
     */
    public static <T> Result<T> error(String message, int code) {
        return new Result<>(null, message, code); // 返回错误信息和状态码
    }

    /**
     * 默认错误返回结果
     *
     * @param message 错误信息
     * @return Result<T> 返回错误结果
     */
    public static <T> Result<T> error(String message) {
        return error(message, 500); // 500 为默认的错误状态码
    }
}
