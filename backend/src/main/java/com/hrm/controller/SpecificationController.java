package com.hrm.controller;

import com.alibaba.fastjson.JSONObject;
import com.hrm.entry.Response;
import com.hrm.pojo.Application;
import com.hrm.pojo.Page;
import com.hrm.pojo.Product;
import com.hrm.pojo.Specification;
import com.hrm.service.SpecificationService;
import com.hrm.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/16
 * @Time 0:37
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/management")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;


    @RequestMapping(value = "/specifications")
    @ResponseBody
    public Object getSpecificationServices(@RequestHeader("token") String token, @RequestBody JSONObject jsonObj) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        Page page_num = new Page((Integer) jsonObj.get("page_num_start"), (Integer) jsonObj.get("page_num_end"));

        try {
            List<Specification> specifications = specificationService.listSpecification(page_num);
            res.code = 0;
            res.data.put("message", "获取所有产品成功");
            res.data.put("specifications", specifications);

        } catch (Exception e) {
            e.printStackTrace();
            res.code = 1;
            res.data.put("message", "获取所有产品失败");
        }

        return res;
    }


    @RequestMapping(value = "/specifications/update")
    @ResponseBody
    public Object updateSpecification(@RequestHeader("token") String token, @RequestBody JSONObject jsonObj) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);


        Integer product_id = (Integer) jsonObj.get("product_id");
        Integer specification_id = (Integer) jsonObj.get("specification_id");
        String specification_name = (String) jsonObj.get("specification_name");
        String specification_type = (String) jsonObj.get("specification_type");
        String description = (String) jsonObj.get("description");
        Integer price = (Integer) jsonObj.get("price");


        Specification specification = new Specification(product_id, specification_id, specification_name, specification_type, price, description);
        try {
            specificationService.updateSpecification(specification);

            res.code = 0;
            res.data.put("message", "更新产品配置信息成功");

        } catch (Exception e) {
            e.printStackTrace();

            res.code = 1;
            res.data.put("message", "更新产品配置信息失败");
        }

        return res;
    }


    @RequestMapping(value = "/specifications/destroy")
    @ResponseBody
    public Object deleteSpecification(@RequestHeader("token") String token, @RequestBody JSONObject jsonObj) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        Integer product_id = (Integer) jsonObj.get("product_id");
        Integer specification_id = (Integer) jsonObj.get("specification_id");


        Specification specification = new Specification(product_id, specification_id, null, null, 0, null);
        try {
            specificationService.deleteSpecification(specification);

            res.code = 0;
            res.data.put("message", "删除产品配置信息成功");

        } catch (Exception e) {
            res.code = 1;
            res.data.put("message", "删除产品配置信息失败");

            e.printStackTrace();
        }

        return res;
    }

    @RequestMapping(value = "/specifications/create")
    @ResponseBody
    public Object addSpecification(@RequestHeader("token") String token, @RequestBody JSONObject jsonObj) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);
        Integer product_id = (Integer) jsonObj.get("product_id");
        Integer specification_id = (Integer) jsonObj.get("specification_id");
        String specification_name = (String) jsonObj.get("specification_name");
        String specification_type = (String) jsonObj.get("specification_type");
        String description = (String) jsonObj.get("description");
        Integer price = (Integer) jsonObj.get("price");

        Specification specification = new Specification(product_id, specification_id, specification_name, specification_type, price, description);
        try {
            specificationService.insertSpecification(specification);

            res.code = 0;
            res.data.put("message", "添加产品配置信息成功");

        } catch (Exception e) {
            res.code = 1;
            res.data.put("message", "添加产品配置信息失败");

            e.printStackTrace();
        }

        return res;
    }
}
