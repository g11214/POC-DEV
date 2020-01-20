package com.ynet.poc.controller;

import com.ynet.poc.common.ResponseVo;
import com.ynet.poc.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
@Api(value = "测试认证")
@Slf4j
public class UserController {
    @Autowired
    LoginService loginService;

    @GetMapping("userInfo")
    @ApiOperation("测试")
    public ResponseVo userInfo(HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        return ResponseVo.SUCCESS().setDataAttribute(loginService.getById(userId));
    }

    @GetMapping("logout")
    public ResponseVo userLogOut(HttpServletRequest request) {
        return ResponseVo.SUCCESS();
    }
}
