package cn.sweetyhut.ezoa.response;

import lombok.Data;

import java.io.Serializable;

/**
 * request请求返回给前端的数据对象
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/25 21:54
 */
@Data
public class MiniResponse<T> implements Serializable {
    private static final long serialVersionUID = -4580168918956264877L;

    private Integer code;
    private String msg;
    private T data;
}
