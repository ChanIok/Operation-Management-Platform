package com.hrm.controller;


import com.alibaba.fastjson.JSONObject;
import com.hrm.constant.Constant;
import com.hrm.constant.MessageConstant;
import com.hrm.entry.Response;
import com.hrm.pojo.Login;
import com.hrm.pojo.UserInfo;
import com.hrm.service.LoginService;
import com.hrm.utils.JWTUtils;
import com.hrm.utils.JsonUtils;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/9
 * @Time 15:58
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    @ResponseBody
    public Object login(@RequestBody JSONObject jsonObj) {

//        创建响应类
        Response res = new Response();
        HashMap<String, String> payload = new HashMap<>();

        //读取json里的信息
        String username = jsonObj.getString("username");
        String password = jsonObj.getString("password");

        //判断是否为空
        String byId = loginService.findById(username);
        if (byId == null) {
//        登录失败，返回状态码:2
            res.code = Constant.CODE_LOGIN_FAILED;
            res.data.put("message", MessageConstant.LOGIN_FAILURE);
            return res;
        }


        if (byId.equals(password)) {
            String byPermission = loginService.findByPermission(username);
            System.out.println(byPermission);

            int idByName = loginService.findIdbyName(username);
            System.out.println(idByName);
            payload.put("user_id", String.valueOf(loginService.findIdbyName(username)));
            String token = JWTUtils.getToken(payload);

            res.data.put("token", token);
            res.data.put("permission", byPermission);
            res.data.put("message", MessageConstant.LOGIN_SUCCESS);

//            登录成功，返回状态码:0
            res.code = Constant.CODE_SUCCESS;
            return res;
        }
        res.data.put("message", MessageConstant.LOGIN_FAILURE);
//        登录失败，返回状态码:2
        res.code = Constant.CODE_LOGIN_FAILED;
        return res;
    }


    @RequestMapping("/register")
    @ResponseBody
    public Object register(@RequestBody JSONObject jsonObj) {

//        创建响应类
        Response res = new Response();
        HashMap<String, String> payload = new HashMap<>();

//      读取json里的信息

//      读取注册名字（不能同名）
        String username = jsonObj.getString("username");

        String byId = loginService.findById(username);

        if (loginService.findById(username) != null) {

            res.code = Constant.CODE_REGISTER_USERNAME_DUPLICATE;
            res.data.put("message", MessageConstant.REUSE_NAME);
            return res;
        }

//      获取注册密码
        String password = jsonObj.getString("password");

        Login login = new Login(0, username, password, Constant.DOMESTIC_USER);

//      注册到user表
        if (1 != loginService.registerUser(login)) {
            res.code = Constant.CODE_REGISTER_DATABASE_OP_FAILED;
            res.data.put("message", MessageConstant.REGISTER_FAILURE);
            return res;
        }

//      获取注册电话号码
        String phone_num = jsonObj.getString("phone_num");

//      获取电子邮箱
        String email = jsonObj.getString("email");

        int idByName = loginService.findIdbyName(username);

        System.out.println(idByName);


//      判断是否插入成功
        if (loginService.setUserInfo(new UserInfo(idByName, phone_num, email)) < 0) {
            res.code = Constant.CODE_REGISTER_DATABASE_OP_FAILED;
            res.data.put("message", MessageConstant.REGISTER_FAILURE);
            return res;
        }

        res.code = Constant.CODE_SUCCESS;
        res.data.put("message", MessageConstant.REGISTER_SUCCESS);

        return res;
    }
}
