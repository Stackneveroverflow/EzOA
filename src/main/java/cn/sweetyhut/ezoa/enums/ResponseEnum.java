package cn.sweetyhut.ezoa.enums;


import lombok.Getter;

/**
 * @author Macer
 */
@Getter
public enum ResponseEnum {
    /**
     * 成功
     */
    OK(0, "OK");

    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
