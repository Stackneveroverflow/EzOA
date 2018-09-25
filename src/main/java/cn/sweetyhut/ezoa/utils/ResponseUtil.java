package cn.sweetyhut.ezoa.utils;

import cn.sweetyhut.ezoa.constant.FrontConst;
import cn.sweetyhut.ezoa.response.MiniResponse;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/25 22:00
 */
public class ResponseUtil {
    public static MiniResponse success(Object obj) {
        MiniResponse<Object> response = new MiniResponse<>();
        response.setCode(FrontConst.CODE_OK);
        response.setMsg("ok");
        response.setData(obj);
        return response;
    }

    public static MiniResponse success() {
        return success(null);
    }

    public static MiniResponse error(Integer code, String msg) {
        MiniResponse response = new MiniResponse();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }
}
