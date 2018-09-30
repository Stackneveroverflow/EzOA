package cn.sweetyhut.ezoa.exception;

import lombok.Getter;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/30 18:45
 */
@Getter
public class Code2SessionException extends RuntimeException {
    private Integer code;

    public Code2SessionException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
