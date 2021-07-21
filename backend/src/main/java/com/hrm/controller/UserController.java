package com.hrm.controller;


import com.alibaba.fastjson.JSONObject;
import com.hrm.constant.Constant;
import com.hrm.constant.MessageConstant;
import com.hrm.entry.Response;

import com.hrm.pojo.*;

import com.hrm.service.BalanceService;
import com.hrm.service.ProductService;
import com.hrm.service.UserService;
import com.hrm.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/info", method = {RequestMethod.GET})
    @ResponseBody
    public Object getUserInfo(@RequestHeader("token") String token) {

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


    @RequestMapping(value = "/info", method = {RequestMethod.POST})
    @ResponseBody
    public Object updateUserInfo(@RequestHeader("token") String token, @RequestBody JSONObject jsonObj) {

//        创建响应类
        Response res = new Response();


//      读取token的id
        int user_id = JWTUtils.getUserId(token);

//        读取json信息，转换为map
        Map<String, Object> userInfo = new HashMap<>();

        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            userInfo.put(entry.getKey(), entry.getValue());
        }
        userInfo.put("user_id", user_id);
        System.out.println("---------------------map对象:" + userInfo.toString());

        try {
            userService.updateUserInfo(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            res.code = 1;
            res.data.put("message", "更新用户信息失败");
            return res;
        }


        res.code = Constant.CODE_SUCCESS;
        res.data.put("message", "更新用户信息成功");
        return res;
    }

    @RequestMapping(value = "/balance", method = {RequestMethod.GET})
    @ResponseBody
    public Object getUserBalance(@RequestHeader("token") String token) {

//        创建响应类
        Response res = new Response();


//      读取token的id
        int user_id = JWTUtils.getUserId(token);
        int balance;

        try {
            balance = balanceService.checkMoneyByID(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            res.code = 1;
            res.data.put("message", "获取用户余额失败");
            return res;
        }


        res.code = Constant.CODE_SUCCESS;
        res.data.put("balance", balance);
        res.data.put("message", "获取用户余额成功");
        return res;
    }

    @RequestMapping(value = "/products")
    @ResponseBody
    public Object findUserProduct(@RequestHeader("token") String token) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);
        try {
            ArrayList<PersonProduct> objectsArrayList = productService.findByProductId(new ID(user_id, null, null, null));
            res.code = 0;
            res.data.put("products",objectsArrayList);
            res.data.put("message","已返回产品详情");
        } catch (Exception e) {
            res.code = 1;
            res.data.put("message","返回产品详情失败");
            e.printStackTrace();
        }
        return res;
    }


    @RequestMapping("/find")
    @ResponseBody
    public Object findUserList(@RequestBody JSONObject jsonObj) {

        //        创建响应类
        Response res = new Response();

        try {
            Map<String, Object> limit = new HashMap<>();

            for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
                limit.put(entry.getKey(), entry.getValue());
            }

            Page page_num = new Page((Integer) limit.get("page_num_start"),(Integer) limit.get("page_num_end"));

            ArrayList<UserList> userList = userService.findUserList(page_num);
            res.code = 0;
            res.data.put("message","返回用户列表成功");
            res.data.put("datalist",userList);

        } catch (Exception e) {
            e.printStackTrace();
            res.code = 0;
            res.data.put("message","返回用户列表失败");
            res.data.put("datalist",null);
        }
        return res;
    }
}
