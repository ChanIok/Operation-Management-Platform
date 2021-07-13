package com.hrm.controller;


import com.hrm.constant.Constant;
import com.hrm.constant.MessageConstant;
import com.hrm.entry.Response;

import com.hrm.pojo.UserInfo;

import com.hrm.service.UserService;
import com.hrm.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/info", method = {RequestMethod.GET})
    @ResponseBody
    public Object register(@RequestHeader("token") String token) {

//        创建响应类
        Response res = new Response();


//      读取token的id
        int user_id = JWTUtils.getUserId(token);

        Object userInfo = userService.findUserInfoById(user_id);


        res.code = Constant.CODE_SUCCESS;
        res.data.put("userInfo", userInfo);
        res.data.put("message", "已返回用户信息");

        return res;
    }
}
