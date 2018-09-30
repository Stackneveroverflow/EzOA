package cn.sweetyhut.ezoa.constant;
/*
 *　　　　　　　 ┏┓      ┏┓+ +
 *           ┏━┛┻━━━━━━┛┻━┓ + +
 *           ┃　　　　　　　┃ + + +
 *           ┃     ━      ┃ + + + +
 *           ┃ ████━████  ┃
 *           ┃            ┃ + +
 *           ┃     ┻      ┃
 *           ┃            ┃ +
 *           ┗━━┓      ┏━━┛
 *              ┃      ┃
 *              ┃      ┃ + + + +
 *              ┃      ┃　　　　 Code is far away from bug with the animal protecting
 *              ┃      ┃ + 　　　　 神兽保佑,代码无bug
 *              ┃      ┃
 *              ┃      ┃　　+
 *              ┃      ┗━━━━━━━┓ + +
 *              ┃              ┣━┓
 *              ┃              ┏━┛
 *              ┗━┓ ┓ ┏━━┳━┓ ┏━┛ + + + +
 *                ┃ ┫ ┫  ┃ ┫ ┫
 *                ┗━┻━┛  ┗━┻━┛+ + + +
 */

/**
 * wx API的常量
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/25 22:10
 */
public interface WxApiConst {
    String GRANT_TYPE = "authorization_code";
    String CODE_NAME = "errcode";
    String MSG_NAME = "errmsg";
    String SKEY_NAME = "session_key";
    String OPENID_NAME = "openid";
    /**
     * code2session返回的errcode
     */
    Integer ERRCODE_OK = 0;
    Integer ERRCODE_BUSY = -1;
    Integer ERRCODE_FAIL = 40029;
    Integer ERRCODE_LIMIT = 45011;

    Integer GENDER_MALE = 1;
    Integer GENDER_FEMALE = 2;
}
