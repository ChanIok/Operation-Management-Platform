package com.hrm.controller;

import com.alibaba.fastjson.JSONObject;
import com.hrm.entry.Response;
import com.hrm.pojo.Advantage;
import com.hrm.pojo.Application;
import com.hrm.service.ApplicationService;
import com.hrm.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 21:26
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value = "/update")
    @ResponseBody
    public Object updateApplication(@RequestHeader("token") String token, Application application) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        try {
            applicationService.updateApplication(application);

            res.code = 0;
            res.data.put("message","更新应用表成功");

        } catch (Exception e) {
            e.printStackTrace();

            res.code = 1;
            res.data.put("message","更新应用表失败");
        }

        return res;
    }


    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object deleteApplication(@RequestHeader("token") String token, Application application) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        try {
            applicationService.deleteApplication(application);

            res.code = 0;
            res.data.put("message","删除应用表成功");

        } catch (Exception e) {
            res.code = 1;
            res.data.put("message","删除应用表失败");

            e.printStackTrace();
        }

        return res;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Object addApplication(@RequestHeader("token") String token, Application application) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        try {
            applicationService.insertApplication(application);

            res.code = 0;
            res.data.put("message","添加应用表成功");

        } catch (Exception e) {
            res.code = 1;
            res.data.put("message","添加应用表失败");

            e.printStackTrace();
        }

        return res;
    }
}
