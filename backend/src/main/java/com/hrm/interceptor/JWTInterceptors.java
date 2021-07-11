package com.hrm.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrm.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/11
 * @Time 8:16
 */
public class JWTInterceptors implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> data = new HashMap<>();

        //获取请求头中的令牌
        String token = request.getHeader("token");

        try{
            JWTUtils.verify(token);
            return true;
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            data.put("message","无效签名");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            data.put("message","token过期");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            data.put("message","token算法不一致");
        }catch (Exception e){
            data.put("message","token无效");
        }

        map.put("code",1);  //设置状态
        map.put("data",data);  //设置状态

        //将map转换为json
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);

        return false;
    }

}
