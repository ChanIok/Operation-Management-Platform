package com.hrm.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/11
 * @Time 9:00
 */
public class JWTUtils {

    private static final String SING = "123456";

    /*
    * 生成token   header.payload.sing
    * */

    public static String getToken(Map<String,String> map){

        Calendar instance = Calendar.getInstance();

        instance.add(Calendar.DAY_OF_YEAR,7);//默认7天过期

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });

        String token = builder.withExpiresAt(instance.getTime())   //指定过期时间
                        .sign(Algorithm.HMAC256(SING)); //SIGN

        return token;
    }


    /*
    *  验证token 合法性
    * */

    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }


    /*
    *   获取token信息的方法
    * */

    public static DecodedJWT getTokenIfo(String token){

        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        return verify;
    }

    /*
    * 删除token
    * */
    public static void logout(String token){

    }

}
