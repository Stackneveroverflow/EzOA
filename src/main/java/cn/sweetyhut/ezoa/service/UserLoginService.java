package cn.sweetyhut.ezoa.service;
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

import java.util.Map;

/**
 * 接口
 * 作用：
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/30 18:11
 */
public interface UserLoginService {
    Map login(String code, String encryptedData, String iv);
}
