package com.hrm.controller;

import com.hrm.constant.Constant;
import com.hrm.entry.Response;
import com.hrm.pojo.*;
import com.hrm.service.*;
import com.hrm.utils.DateUtils;
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

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TradeEXService tradeEXService;

    @Autowired
    private AuthorityService authorityService;

    @RequestMapping(value = "/products", method = {RequestMethod.GET})
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

    //        获取单个具体的体验产品信息
    @RequestMapping(value = "/products/{product_id}", method = {RequestMethod.GET})
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


    //       领取单个体验产品
    @RequestMapping(value = "/products/{product_id}/{specification_id}/settlement", method = {RequestMethod.GET})
    @ResponseBody

    public Object getProductSettlement(@PathVariable("product_id") String product_id, @PathVariable("specification_id") String specification_id, @RequestHeader("token") String token) {

        HashMap<String, Object> details = new HashMap<>();

//           创建响应类
        Response res = new Response();

//        在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        ID id = new ID(user_id, Integer.parseInt(product_id), null, null);

//        创建product对象
        String product_name = productService.findById(Integer.parseInt(product_id)).getProduct_name();


        Integer balanceUser = balanceService.checkUser(user_id);
        if (balanceUser == null) {
            res.code = 1;
            res.data.put("message", "无账户");
            return res;
        }

        try {

            //获取当前时间
            String currentDatetime = DateUtils.getCurrentDatetime();

            //设置过期时间
            String dueDatetime = DateUtils.setTrialDueDatetime(currentDatetime, 1);

            System.out.println("当前时间" + currentDatetime);
            System.out.println("过期时间" + dueDatetime);


            //创建体验流水信息
            TradeEX tradeEX = new TradeEX(null, Integer.parseInt(product_id), user_id, Integer.parseInt(specification_id), product_name, currentDatetime, 1);


            try {
                //插入体验流水信息
                tradeEXService.insertTrade(tradeEX);
            } catch (Exception e) {
                e.printStackTrace();
                res.code = 1;
                res.data.put("message", "插入表失败");
                return res;
            }


            //创建用户产品权限信息
            Authority authorityTemp = new Authority(user_id, Integer.parseInt(product_id), product_name, Integer.parseInt(specification_id), dueDatetime);

            //往产品权限表中添加用户信息

            Authority authority = null;

            authority = authorityService.checkAuthority(authorityTemp);

            //如果产品权限表中无该条数据，则直接插入表中
            if (authority == null) {
                authorityService.insertAuthority(authorityTemp);
            }

            //如果表中有该条数据，则领取失败，因为只能领取一次
            else {
                res.code = 1;
                res.data.put("message", "您已领取该产品");
                return res;
            }
            res.code = 0;
            res.data.put("message", "领取成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.code = 1;
            res.data.put("message", "领取失败");
            return res;
        }
        return res;
    }

}