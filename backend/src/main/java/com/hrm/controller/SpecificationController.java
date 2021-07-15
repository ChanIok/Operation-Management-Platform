package com.hrm.controller;

import com.hrm.entry.Response;
import com.hrm.pojo.Application;
import com.hrm.pojo.Specification;
import com.hrm.service.SpecificationService;
import com.hrm.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/16
 * @Time 0:37
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/specification")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    @RequestMapping(value = "/update")
    @ResponseBody
    public Object updateSpecification(@RequestHeader("token") String token, Specification specification) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        try {
            specificationService.updateSpecification(specification);

            res.code = 0;
            res.data.put("message","更新产品配置信息表成功");

        } catch (Exception e) {
            e.printStackTrace();

            res.code = 1;
            res.data.put("message","更新产品配置信息表失败");
        }

        return res;
    }


    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object deleteSpecification(@RequestHeader("token") String token, Specification specification) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        try {
            specificationService.deleteSpecification(specification);

            res.code = 0;
            res.data.put("message","删除产品配置信息表成功");

        } catch (Exception e) {
            res.code = 1;
            res.data.put("message","删除产品配置信息表失败");

            e.printStackTrace();
        }

        return res;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Object addSpecification(@RequestHeader("token") String token, Specification specification) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        try {
           specificationService.insertSpecification(specification);

            res.code = 0;
            res.data.put("message","添加产品配置信息表成功");

        } catch (Exception e) {
            res.code = 1;
            res.data.put("message","添加产品配置信息表失败");

            e.printStackTrace();
        }

        return res;
    }
}
