package com.ynet.poc.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ynet.poc.entity.LoginInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {
    @Value("${ynet.auth.secret}")
    private String secret;

    @Value("${ynet.auth.expire-time}")
    private int expireTime;

    public String createToken(LoginInfo loginInfo) {
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                    //.withIssuer("ynet")
                    .withClaim("loginInfo", JSON.toJSONString(loginInfo))
                    .withExpiresAt(new Date(System.currentTimeMillis() + 360000000))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

    public DecodedJWT verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    //.withIssuer("ynet")
                    .build(); //Reusable verifier instance
            jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
        }
        return jwt;
    }

}
