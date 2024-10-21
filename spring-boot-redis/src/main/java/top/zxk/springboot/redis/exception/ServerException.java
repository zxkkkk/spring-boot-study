package top.zxk.springboot.redis.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.zxk.springboot.redis.enums.ErrorCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServerException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final int code;
    private final String msg;

    public ServerException(String msg) {
        super(msg);
        this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode(); // 默认错误码
        this.msg = msg;
    }

    public ServerException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public ServerException(String msg, Throwable e) {
        super(msg, e);
        this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode(); // 默认错误码
        this.msg = msg;
    }
}
