package com.hrm.controller;

import com.hrm.constant.Constant;
import com.hrm.entry.Response;
import com.hrm.pojo.Product;
import com.hrm.service.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/mall")
public class MallController {

    @Autowired
    private MallService mallService;

    @RequestMapping("/products")
    @ResponseBody
    public Object listProducts() {

        List<Object> products = new ArrayList<>();
        String[] types = {"弹性计算", "存储", "数据库", "安全", "大数据", "人工智能"};

//        创建响应类
        Response res = new Response();

        for (String item : types) {
            HashMap<String, Object> productsInType = new HashMap<>();
            productsInType.put("type", item);
            productsInType.put("list", mallService.listProductsByType(item));
            products.add(productsInType);
        }

        res.code = Constant.CODE_SUCCESS;
        res.data.put("message", "已返回产品");
        res.data.put("products", products);
        return res;
    }
}