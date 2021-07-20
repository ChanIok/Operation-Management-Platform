package com.hrm.controller;

import com.alibaba.fastjson.JSONObject;
import com.hrm.entry.Response;
import com.hrm.pojo.*;
import com.hrm.service.ProductService;
import com.hrm.service.SpecificationService;
import com.hrm.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/16
 * @Time 0:43
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/management")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products")
    @ResponseBody
    public Object getProducts(@RequestHeader("token") String token, @RequestBody JSONObject jsonObj) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        Page page_num = new Page((Integer) jsonObj.get("page_num_start"), (Integer) jsonObj.get("page_num_end"));

        try {
            List<Product> products = productService.listProducts(page_num);
            res.code = 0;
            res.data.put("message", "获取所有产品成功");
            res.data.put("products", products);

        } catch (Exception e) {
            e.printStackTrace();
            res.code = 1;
            res.data.put("message", "获取所有产品失败");
        }

        return res;
    }


    @RequestMapping(value = "/products/update")
    @ResponseBody
    public Object updateProduct(@RequestHeader("token") String token, @RequestBody JSONObject jsonObj) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        Integer product_id =  (Integer) jsonObj.get("product_id");
        String product_name = (String) jsonObj.get("product_name");
        String introduction = (String) jsonObj.get("introduction");
        String product_type = (String) jsonObj.get("product_type");
        String img_url = (String) jsonObj.get("img_url");
        Product product = new Product(product_id, product_name, introduction, product_type, img_url);

        try {
            productService.updateProduct(product);
            res.code = 0;
            res.data.put("message", "更新产品成功");

        } catch (Exception e) {
            e.printStackTrace();
            res.code = 1;
            res.data.put("message", "更新产品失败");
        }

        return res;
    }


    @RequestMapping(value = "/products/destroy")
    @ResponseBody
    public Object deleteProduct(@RequestHeader("token") String token, @RequestBody JSONObject jsonObj) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        Integer product_id =  (Integer) jsonObj.get("product_id");
        String product_name = (String) jsonObj.get("product_name");
        String introduction = (String) jsonObj.get("introduction");
        String product_type = (String) jsonObj.get("product_type");
        String img_url = (String) jsonObj.get("img_url");
        Product product = new Product(product_id, product_name, introduction, product_type, img_url);

        try {
            productService.deleteProductInOtherTable(product);
            productService.deleteProduct(product);

            res.code = 0;
            res.data.put("message", "删除产品表成功");

        } catch (Exception e) {
            res.code = 1;
            res.data.put("message", "删除产品表失败");

            e.printStackTrace();
        }

        return res;
    }

    @RequestMapping(value = "/products/create")
    @ResponseBody
    public Object addProduct(@RequestHeader("token") String token, @RequestBody JSONObject jsonObj) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        Integer product_id =  (Integer) jsonObj.get("product_id");
        String product_name = (String) jsonObj.get("product_name");
        String introduction = (String) jsonObj.get("introduction");
        String product_type = (String) jsonObj.get("product_type");
        String img_url = (String) jsonObj.get("img_url");
        Product product = new Product(product_id, product_name, introduction, product_type, img_url);

        try {
            productService.insertProduct(product);

            res.code = 0;
            res.data.put("message", "添加产品表成功");

        } catch (Exception e) {
            res.code = 1;
            res.data.put("message", "添加产品表失败");

            e.printStackTrace();
        }
        return res;
    }
}
