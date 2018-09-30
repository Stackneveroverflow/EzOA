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

import cn.sweetyhut.ezoa.response.MiniResponse;

/**
 * 接口
 * 作用：
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/30 16:52
 */
public interface UserAttendanceService {

    MiniResponse check(String openid, String ssid, String bssid);

    MiniResponse checkStatus(String openid);

    MiniResponse getAttendanceLog(String openid);

    void saveWorkHoursDaily();
}
