package com.hrm.controller;

import com.hrm.constant.Constant;
import com.hrm.entry.Response;
import com.hrm.service.MallService;
import com.hrm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/16
 * @Time 19:21
 */
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/trial")
public class TrialController {
    @Autowired
    private MallService mallService;

    @Autowired
    private ProductService productService;


    @RequestMapping("/products")
    @ResponseBody

//获取所有的体验产品
    public Object listTrialProducts() {
        List<Object> products = new ArrayList<>();
        String[] types = {"弹性计算", "存储", "数据库", "安全", "大数据", "人工智能"};

//        创建响应类
        Response res = new Response();
        for (String item : types) {
            HashMap<String, Object> productsInType = new HashMap<>();
            productsInType.put("product_type", item);
            productsInType.put("list", productService.listTrialProductsByType(item));
            products.add(productsInType);
        }
        res.code = Constant.CODE_SUCCESS;
        res.data.put("message", "已返回所有体验产品");
        res.data.put("products", products);
        return res;
    }

    //        获取单个具体的体验产品
    @RequestMapping("/products/{product_id}")
    @ResponseBody

    public Object getProductDetails(@PathVariable("product_id") String product_id) {
        HashMap<String, Object> details = new HashMap<>();
        String[] types = {"specification", "advantage", "application"};
//        创建响应类
        Response res = new Response();
        details.put("info", mallService.fineProductById(Integer.parseInt(product_id)));
        details.put(types[0], mallService.listTrialSpecificationsByProductId(Integer.parseInt(product_id)));
        details.put(types[1], mallService.listAdvantagesByProductId(Integer.parseInt(product_id)));
        details.put(types[2], mallService.listApplicationsByProductId(Integer.parseInt(product_id)));
        res.code = Constant.CODE_SUCCESS;
        res.data.put("details", details);
        res.data.put("message", "已返回产品详情");
        return res;
    }
}