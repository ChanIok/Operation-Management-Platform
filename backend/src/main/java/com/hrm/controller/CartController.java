package com.hrm.controller;


import com.alibaba.fastjson.JSONObject;
import com.hrm.entry.Response;


import org.springframework.web.bind.annotation.*;

import com.hrm.utils.JWTUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author zjw
 * @package backend
 * @Date 2021/7/11
 * @Time 23:30
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cart")
public class CartController {

    @RequestMapping("/add")
    @ResponseBody
    public int addProduct(HttpServletRequest request) {

//        创建响应类
        Response res = new Response();

        String product_id1 = request.getParameter("product_id");

        String add_num = request.getParameter("add_num");
        System.out.println(product_id1 + add_num);



       /* Integer product_id = jsonObj.getInteger("product_id");
        Integer add_num = jsonObj.getInteger("add_num");*/

        String token = request.getHeader("token");

        System.out.println(token);

        int user_id = JWTUtils.getUserId(token);
        System.out.println(user_id);
        return 0;
    }


    @RequestMapping("/test")
    @ResponseBody
    public Object Test(@RequestHeader("token") String token, @RequestBody JSONObject jsonObj) {
        //        创建响应类
        Response res = new Response();

        Integer user_id = JWTUtils.getUserId(token);
        //读取json里的信息
        String test = jsonObj.getString("test");

        res.data.put("id", user_id);
        res.data.put("info", test);
        res.code = 0;
        return res;
    }
}
