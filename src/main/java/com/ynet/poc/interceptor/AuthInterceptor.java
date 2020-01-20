package com.ynet.poc.interceptor;

import com.alibaba.fastjson.JSON;
import com.ynet.poc.entity.LoginInfo;
import com.ynet.poc.exception.YNETException;
import com.ynet.poc.util.JwtUtils;
import com.ynet.poc.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String token = request.getHeader("token");
        log.info(token);
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
            if (StringUtils.isEmpty(token))
                throw new YNETException("300", "用户未登录");
        }
        if (jwtUtils.verifyToken(token) == null)
            throw new YNETException("500", "用户登录状态失效");

        String json = jwtUtils.verifyToken(token).getClaim("loginInfo").asString();
        int userId = JSON.parseObject(json, LoginInfo.class).getUserId();
        log.info("用户id为：" + userId);
        request.setAttribute("userId", userId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
