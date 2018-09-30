package cn.sweetyhut.ezoa.controller;

import cn.sweetyhut.ezoa.response.MiniResponse;
import cn.sweetyhut.ezoa.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
        return userLoginService.login(code, encryptedData, iv);
    }
}
