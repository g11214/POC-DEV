package com.ynet.poc.controller;

import com.ynet.poc.common.Constants;
import com.ynet.poc.common.ResponseVo;
import com.ynet.poc.service.LoginService;
import com.ynet.poc.service.ProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Api(value = "登录api接口")
@Slf4j
public class LoginController {

    LoginService loginService;
    ProducerService producerService;

    @Autowired
    public LoginController(LoginService loginService,
                           ProducerService producerService) {
        this.loginService = loginService;
        this.producerService = producerService;
    }

    @GetMapping("code")
    @ApiOperation("获取短信验证码")
    public ResponseVo getSmsCode(@RequestParam("mobile") String mobileNumber) {
        boolean flag = mobileNumber.matches("^[1][3,4,5,7,8][0-9]{9}$");
        if (!flag) return ResponseVo.FAIL("手机格式不正确");
        if (loginService.coutSmsCode(mobileNumber) < Constants.SMS_MAX_TIME)
            producerService.getLoginSmsCode(mobileNumber);
        else
            return ResponseVo.FAIL("超过今日短信最大获取次数");
        return ResponseVo.SUCCESS();
    }

    @GetMapping
    @ApiOperation("手机短信注册登录")
    public ResponseVo getSmsCode(@RequestParam("mobile") String mobileNumber,
                                 @RequestParam("code") String smsCode) {
        String token = loginService.loginOrRegister(mobileNumber, smsCode);
        return ResponseVo.SUCCESS().setDataAttribute(token);
    }

}
