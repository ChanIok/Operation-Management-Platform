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

        try {
            Object userInfo = userService.findUserById(user_id);

            res.code = 0;
            res.data.put("message", "返回用户信息成功");
            res.data.put("userMessage", userInfo);

        } catch (Exception e) {

            e.printStackTrace();

            res.code = 0;
            res.data.put("message", "返回用户信息失败");
            res.data.put("userMessage", null);
        }


        return res;
    }

}
