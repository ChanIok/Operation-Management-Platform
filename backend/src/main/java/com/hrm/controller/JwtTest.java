package com.hrm.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.AlgorithmMethod;
import java.util.Calendar;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/11
 * @Time 1:26
 */
public class JwtTest {
    static String key = "123456abc";
    static  String sign;
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.SECOND,60);

        JWTCreator.Builder builder = JWT.create().withClaim("username",123)
                .withClaim("password",123)
                .withClaim("url","localhost")
                .withExpiresAt(calendar.getTime());
         sign = builder.sign(Algorithm.HMAC256(key));
        System.out.println(sign);
    }

    @Test
    public void testVerify(){
        String sign = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6MTIzLCJleHAiOjE2MjU5Mzg4MDMsInVybCI6ImxvY2FsaG9zdCIsInVzZXJuYW1lIjoxMjN9.cu2PIeds0LRgLzQWk9fyvLNf_JkuQVozkaG8N90BAsk";
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(key)).build().verify(sign);
        System.out.println(verify);
    }
}
