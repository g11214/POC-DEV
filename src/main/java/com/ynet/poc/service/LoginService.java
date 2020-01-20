package com.ynet.poc.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ynet.poc.entity.LoginInfo;
import com.ynet.poc.entity.SmsCode;
import com.ynet.poc.exception.YNETException;
import com.ynet.poc.mapper.LoginMapper;
import com.ynet.poc.util.AliyunSmsUtil;
import com.ynet.poc.util.JwtUtils;
import com.ynet.poc.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LoginService extends ServiceImpl<LoginMapper, LoginInfo> {

    JwtUtils jwtUtils;
    AliyunSmsUtil smsUtil;
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    LoginService(JwtUtils jwtUtils,
                 AliyunSmsUtil smsUtil,
                 RedisTemplate<String, Object> redisTemplate) {
        this.jwtUtils = jwtUtils;
        this.smsUtil = smsUtil;
        this.redisTemplate = redisTemplate;
    }

    /*public String login(String username, String password) {
        QueryWrapper<LoginInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("pur_user_name", username)
                .eq("pur_state", 0);
        LoginInfo loginInfo = this.getOne(wrapper);
        log.info("password:" + loginInfo.getPassword());
        if (loginInfo.getPassword().equals(password)) {
            String token = jwtUtils.createToken(loginInfo);
            log.info("===========>Token:" + token);
            return token;
        }
        throw new YNETException("401", "登录失败");
    }*/

    public String loginOrRegister(String mobile, String smsCode) {
        String token;
        QueryWrapper<LoginInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("pur_mobile_no", mobile)
                .eq("pur_state", 0);
        LoginInfo loginInfo = this.getOne(wrapper);
        if (loginInfo != null) {
            if (checkSmsCode(mobile, smsCode)) {
                //生成token
                token = jwtUtils.createToken(loginInfo);
                log.info("Token：" + token);
                //执行登录成功后修改操作

            } else
                throw new YNETException("4011", "验证码已失效");

        } else {
            if (checkSmsCode(mobile, smsCode)) {
                //执行注册操作
                loginInfo = new LoginInfo(mobile);
                this.save(loginInfo);
                //生成token
                token = jwtUtils.createToken(this.getOne(wrapper));
                log.info("Token：" + token);
            } else
                throw new YNETException("4011", "验证码错误");
        }
        return token;
    }

    private Boolean checkSmsCode(String mobile, String smsCode) {
        try {
            String json = (String) redisTemplate.opsForValue().get("sms:" + mobile);
            SmsCode smsCode1 = JSON.parseObject(json, SmsCode.class);
            return smsCode1.getCode().equals(smsCode);
        } catch (NullPointerException e) {
            throw new YNETException("4011", "验证码已失效");
        }
    }


    public LoginInfo getUserInfoByToken(String token) throws NullPointerException {
        String loginInfoJson = jwtUtils.verifyToken(token).getClaim("loginInfo").asString();
        return JSON.parseObject(loginInfoJson, LoginInfo.class);
    }

    public void getSmsCode(String mobileNumber) {
        int count = coutSmsCode(mobileNumber);
        if (count < 5) {
            //执行发送验证码
            sendSmsCode(mobileNumber, count);
        }
    }

    public int coutSmsCode(String mobile) {
        int count = 0;
        try {
            ValueOperations<String, Object> operation = redisTemplate.opsForValue();
            String value = (String) operation.get("sms:" + mobile + ":count");
            System.out.println(value);
            if (StringUtils.isNotEmpty(value))
                count = Integer.parseInt(value);
        } catch (NullPointerException e) {

        }
        return count;
    }

    private void sendSmsCode(String mobileNumber, long count) {
        String code = smsUtil.getCode();
        log.info(code);
        SmsCode smsCode = new SmsCode(mobileNumber, code, new Date(new Date().getTime() + 15 * 60 * 1000));
        redisTemplate.opsForValue().set("sms:" + mobileNumber, JSON.toJSONString(smsCode), 15, TimeUnit.MINUTES);
        if (count == 0)
            redisTemplate.opsForValue().set("sms:" + mobileNumber + ":count", Long.toString(count + 1), 1, TimeUnit.DAYS);
        else
            redisTemplate.opsForValue().increment("sms:" + mobileNumber + ":count");
        //smsUtil.sendSmsCode(mobileNumber, code);
    }

    private void sendSmsCode(String mobileNumber) {
        sendSmsCode(mobileNumber, 0);
    }

}
