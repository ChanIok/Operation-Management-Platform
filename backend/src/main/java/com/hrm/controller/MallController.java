package com.hrm.controller;

import com.hrm.constant.Constant;
import com.hrm.entry.Response;
import com.hrm.pojo.Advantage;
import com.hrm.pojo.Cart;
import com.hrm.pojo.Product;
import com.hrm.service.MallService;
import com.hrm.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            productsInType.put("product_type", item);
            productsInType.put("list", mallService.listProductsByType(item));
            products.add(productsInType);
        }

        res.code = Constant.CODE_SUCCESS;
        res.data.put("message", "已返回所有产品");
        res.data.put("products", products);
        return res;
    }

    @RequestMapping("/products/{product_id}")
    @ResponseBody

    public Object getProductDetails(@PathVariable("product_id") String product_id) {
        HashMap<String, Object> details = new HashMap<>();
        String[] types = {"specification", "advantage", "application"};
//        创建响应类
        Response res = new Response();
        details.put("info", mallService.fineProductById(Integer.parseInt(product_id)));
        details.put(types[0], mallService.listSpecificationsByProductId(Integer.parseInt(product_id)));
        details.put(types[1], mallService.listAdvantagesByProductId(Integer.parseInt(product_id)));
        details.put(types[2], mallService.listApplicationsByProductId(Integer.parseInt(product_id)));
        res.code = Constant.CODE_SUCCESS;
        res.data.put("details", details);
        res.data.put("message", "已返回产品详情");
        return res;
    }


}