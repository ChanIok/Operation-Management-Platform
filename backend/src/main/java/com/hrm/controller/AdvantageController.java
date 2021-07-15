package com.hrm.controller;

import com.alibaba.fastjson.JSONObject;
import com.hrm.entry.Response;
import com.hrm.pojo.Advantage;
import com.hrm.service.AdvantageService;
import com.hrm.utils.JWTUtils;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 18:35
 */


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/advantage")
public class AdvantageController {

    @Autowired
    private AdvantageService advantageService;


    //查找优势表
    @RequestMapping(value = "/get")
    @ResponseBody
    public Object getAdvantage(@RequestHeader("token") String token, @RequestBody JSONObject jsonObj) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        Integer product_id = jsonObj.getInteger("product_id");

        if (product_id == null){
            res.code = 0;
            res.data.put("message","返回优势表失败");
            res.data.put("trolley",null);
            return res;
        }

        try {
            List<Advantage> advantages = advantageService.listAdvantagesByProductId(product_id);

            res.code = 0;
            res.data.put("message","返回优势表成功");
            res.data.put("trolley",advantages);

        } catch (Exception e) {

            res.code = 0;
            res.data.put("message","返回优势表失败");
            res.data.put("trolley",null);
            e.printStackTrace();
        }

        return res;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Object updateAdvantage(@RequestHeader("token") String token, Advantage advantage) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        try {
            advantageService.updateAdvantage(advantage);

            res.code = 0;
            res.data.put("message","更新优势表成功");

        } catch (Exception e) {
            e.printStackTrace();

            res.code = 1;
            res.data.put("message","更新优势表失败");
        }

        return res;
    }


    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object deleteAdvantage(@RequestHeader("token") String token, Advantage advantage) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        try {
            advantageService.deleteAdvantage(advantage);

            res.code = 0;
            res.data.put("message","删除成功");

        } catch (Exception e) {
            res.code = 1;
            res.data.put("message","删除失败");

            e.printStackTrace();
        }

        return res;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Object addAdvantage(@RequestHeader("token") String token, Advantage advantage) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        try {
            advantageService.insertAdvantage(advantage);

            res.code = 0;
            res.data.put("message","添加成功");

        } catch (Exception e) {
            res.code = 1;
            res.data.put("message","添加失败");

            e.printStackTrace();
        }

        return res;
    }

}
