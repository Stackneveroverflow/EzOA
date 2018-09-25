package cn.sweetyhut.ezoa.response;

import lombok.Data;

/**
 * request请求返回给前端的数据对象
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/25 21:54
 */
@Data
public class MiniResponse<T> {
    private Integer code;
    private String msg;
    private T data;
}
