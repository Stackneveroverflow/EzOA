package cn.sweetyhut.ezoa.controller;

import cn.sweetyhut.ezoa.exception.Code2SessionException;
import cn.sweetyhut.ezoa.response.MiniResponse;
import cn.sweetyhut.ezoa.service.UserLoginService;
import cn.sweetyhut.ezoa.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/30 18:20
 */
@RestController
@RequestMapping("/user")
public class UserLoginController {
    private UserLoginService userLoginService;

    @Autowired
    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @ResponseBody
    @GetMapping("/login")
    public MiniResponse login(String code, String encryptedData, String iv) {
        try {
            Map data = userLoginService.login(code, encryptedData, iv);
            return ResponseUtil.success(data);
        } catch (Code2SessionException e) {
            return ResponseUtil.error(e.getCode(), e.getMessage());
        } finally {
            userLoginService = null;
        }
    }
}
