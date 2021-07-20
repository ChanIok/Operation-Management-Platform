package com.hrm.controller;


import com.alibaba.fastjson.JSONObject;
import com.hrm.constant.Constant;
import com.hrm.constant.MessageConstant;
import com.hrm.entry.Response;
import com.hrm.pojo.Auth;
import com.hrm.pojo.User;
import com.hrm.pojo.UserInfo;
import com.hrm.service.BalanceService;
import com.hrm.service.AuthService;
import com.hrm.service.UserService;
import com.hrm.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    private AuthService authService;



    @Autowired
    private BalanceService balanceService;

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
        Map<String,String> PasswordAndSalt = authService.getPasswordAndSaltByUsername(username);
        if (PasswordAndSalt == null) {
//        登录失败，返回状态码:2
            res.code = Constant.CODE_LOGIN_FAILED;
            res.data.put("message", MessageConstant.LOGIN_FAILURE);
            return res;
        }
        String passwordWithSalt = PasswordAndSalt.get("password");
        String salt  = PasswordAndSalt.get("salt");


        if (passwordWithSalt.equals(DigestUtils.md5DigestAsHex((password + salt).getBytes()))) {
            int byPermission = authService.getPermissionByUsername(username);
            System.out.println(byPermission);

            int idByName = authService.findIdByName(username);
            System.out.println(idByName);
            payload.put("user_id", String.valueOf(authService.findIdByName(username)));
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

    @RequestMapping("/management-login")
    @ResponseBody
    public Object managementLogin(@RequestBody JSONObject jsonObj) {

//        创建响应类
        Response res = new Response();
        HashMap<String, String> payload = new HashMap<>();

        //读取json里的信息
        String username = jsonObj.getString("username");
        String password = jsonObj.getString("password");



        //判断是否为空
        Map<String,String> PasswordAndSalt = authService.getPasswordAndSaltByUsername(username);
        if (PasswordAndSalt == null) {
//        登录失败，返回状态码:2
            res.code = Constant.CODE_LOGIN_FAILED;
            res.data.put("message", MessageConstant.LOGIN_FAILURE);
            return res;
        }
        String passwordWithSalt = PasswordAndSalt.get("password");
        String salt  = PasswordAndSalt.get("salt");


        if (passwordWithSalt.equals(DigestUtils.md5DigestAsHex((password + salt).getBytes()))) {
            int byPermission = authService.getPermissionByUsername(username);
//            越权登录
            if(byPermission>3){
                res.code = Constant.CODE_LOGIN_FAILED;
                res.data.put("message", "越权登录");
                return res;
            }

            int idByName = authService.findIdByName(username);
            System.out.println(idByName);
            payload.put("user_id", String.valueOf(authService.findIdByName(username)));
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

        String byId = authService.findById(username);

        if (authService.findById(username) != null) {

            res.code = Constant.CODE_REGISTER_USERNAME_DUPLICATE;
            res.data.put("message", MessageConstant.REUSE_NAME);
            return res;
        }

//      获取注册密码
        String password = jsonObj.getString("password");

//        生成随机数来获取盐
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        String salt = sb.toString();
//        加盐
        String passwordWithSalt = DigestUtils.md5DigestAsHex((password + salt).getBytes());

        User user = new User(0, username, passwordWithSalt, Constant.DOMESTIC_USER, salt);

//      注册到user表
        if (1 != authService.registerUser(user)) {
            res.code = Constant.CODE_REGISTER_DATABASE_OP_FAILED;
            res.data.put("message", MessageConstant.REGISTER_FAILURE);
            return res;
        }

//      获取注册电话号码
        String phone_num = jsonObj.getString("phone_num");

//      获取电子邮箱
        String email = jsonObj.getString("email");

        int idByName = authService.findIdByName(username);

        System.out.println(idByName);


//      判断是否插入成功
        if (authService.setUserInfo(new UserInfo(idByName, phone_num, email)) < 0) {
            res.code = Constant.CODE_REGISTER_DATABASE_OP_FAILED;
            res.data.put("message", MessageConstant.REGISTER_FAILURE);
            return res;
        }

        //初始化账户
        int user_id = authService.findIdByName(username);
        balanceService.addBalance(user_id);

        res.code = Constant.CODE_SUCCESS;
        res.data.put("message", MessageConstant.REGISTER_SUCCESS);

        return res;
    }
}
