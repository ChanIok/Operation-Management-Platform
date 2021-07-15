package com.hrm.controller;

import com.hrm.entry.Response;
import com.hrm.pojo.Product;
import com.hrm.pojo.Specification;
import com.hrm.service.ProductService;
import com.hrm.service.SpecificationService;
import com.hrm.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/16
 * @Time 0:43
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/update")
    @ResponseBody
    public Object updateProduct(@RequestHeader("token") String token, Product product) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        try {
            productService.updateProduct(product);

            res.code = 0;
            res.data.put("message","更新产品表成功");

        } catch (Exception e) {
            e.printStackTrace();

            res.code = 1;
            res.data.put("message","更新产品表失败");
        }

        return res;
    }


    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object deleteProduct(@RequestHeader("token") String token, Product product) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        try {
           productService.deleteProduct(product);

            res.code = 0;
            res.data.put("message","删除产品表成功");

        } catch (Exception e) {
            res.code = 1;
            res.data.put("message","删除产品表失败");

            e.printStackTrace();
        }

        return res;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Object addProduct(@RequestHeader("token") String token, Product product) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        try {
            productService.insertProduct(product);

            res.code = 0;
            res.data.put("message","添加产品表成功");

        } catch (Exception e) {
            res.code = 1;
            res.data.put("message","添加产品表失败");

            e.printStackTrace();
        }

        return res;
    }
}
