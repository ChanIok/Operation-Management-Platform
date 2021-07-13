package com.hrm.controller;


import com.alibaba.fastjson.JSONObject;
import com.hrm.entry.Response;


import com.hrm.pojo.Balance;
import com.hrm.pojo.Cart;
import com.hrm.pojo.Product;
import com.hrm.service.BalanceService;
import com.hrm.service.CartService;
import com.hrm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hrm.utils.JWTUtils;


import javax.servlet.http.HttpServletRequest;

import java.security.PublicKey;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;


/**
 * @author zjw
 * @package backend
 * @Date 2021/7/11
 * @Time 23:30
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BalanceService balanceService;

    @RequestMapping("/update")
    @ResponseBody
    public Object updateProduct(HttpServletRequest request, @RequestBody ArrayList<Map<String,Integer>> cartList) {

//        创建响应类
        Response res = new Response();

        String token = request.getHeader("token");

        int user_id = JWTUtils.getUserId(token);

        ArrayList<Cart> cartObjList = new ArrayList<Cart>();

        for (int i = 0; i < cartList.size(); i++) {
            cartObjList.add(new Cart(user_id,null,null));
        }

        System.out.println("----------------------------2");

        int controller = 0;
        int index = 0;

        for (Map<String,Integer> map: cartList) {
            for (String key: map.keySet()
            ) {

                if (controller == 2) {
                    index++;
                    controller = 0;
                }

                if (controller == 0)
                    cartObjList.get(index).setProduct_id(map.get(key));
                else if (controller == 1)
                    cartObjList.get(index).setBuy_count(map.get(key));

                controller++;
            }
        }

        System.out.println("---------------------3");

        for (int i = 0; i < cartList.size(); i++) {

            Cart checkCart = cartService.checkCart(cartObjList.get(i));

            System.out.println("---------------------4");

            if (checkCart == null){
               cartService.insertCart(cartObjList.get(i));

                System.out.println("---------------------5");
            }else{

                cartService.updateProduct(cartObjList.get(i));
            }

        }
        return 0;
    }

    @RequestMapping("/settlement")
    @ResponseBody
    public Object calculateCart(HttpServletRequest request){

        //        创建响应类
        Response res = new Response();

        //在token里获取用户id
        int user_id = JWTUtils.getUserId(request.getHeader("token"));


        return 0;
    }

    @RequestMapping("/purchase")
    @ResponseBody
    public Object ClearCart(HttpServletRequest request, @RequestBody ArrayList<Map<String,Integer>> cartList){

        //        创建响应类
        Response res = new Response();

        String token = request.getHeader("token");

        int user_id = JWTUtils.getUserId(token);


        ArrayList<Cart> cartObjList = new ArrayList<Cart>();

        ArrayList<Product> productObjList = new ArrayList<Product>();

        for (int i = 0; i < cartList.size(); i++) {         //前端传输来的数据
            cartObjList.add(new Cart(user_id,null,null));
        }

        System.out.println("----------------------------2");

        int controller = 0;
        int index = 0;

        for (Map<String,Integer> map: cartList) {
            for (String key: map.keySet()
            ) {

                if (controller == 2) {
                    index++;
                    controller = 0;
                }

                if (controller == 0)
                    cartObjList.get(index).setProduct_id(map.get(key));

                else if (controller == 1)
                    cartObjList.get(index).setBuy_count(map.get(key));

                controller++;
            }
        }

        System.out.println("---------------------3");

        int aliPay = 0;     //需要支付的总额

        int userMoney = balanceService.checkMoneyByID(user_id);     //查询用户的余额
        System.out.println("用户余额：  " +  userMoney);

        for (int i = 0; i < cartList.size(); i++) {                 //查找product表中有关的数据
          productObjList.add( productService.findById(cartObjList.get(i).getProduct_id()));
        }

        //计算所需的金额
        for (int i = 0; i <cartList.size(); i++) {
           aliPay = aliPay +  productObjList.get(i).getPrice() * cartObjList.get(i).getBuy_count() ;
        }

        System.out.println("所需金额" + aliPay);

        if (userMoney < aliPay){
            res.code = 1;
            res.data.put("message","用户余额不足");
            return res;
        }

        for (int i = 0; i < cartList.size() ; i++) {

            int buy_count = cartObjList.get(i).getBuy_count();
            int surplus = productObjList.get(i).getSurplus();

            if (buy_count > surplus){

                res.code = 1;
                res.data.put("message","商品库存不足") ;

                return res;
            }
        }

        System.out.println("---------------------4");

        //更新用户信息
        int money = userMoney - aliPay;
        System.out.println("money :" + money);
        balanceService.updateMoneyById(new Balance(user_id,money));

        //更新产品库存
        for (int i = 0; i < cartList.size(); i++) {

            int buy_count = cartObjList.get(i).getBuy_count();
            int surplus = productObjList.get(i).getSurplus();

            System.out.println("产品库存为" + surplus);
            System.out.println(surplus - buy_count);

            productObjList.get(i).setSurplus(surplus - buy_count);
            System.out.println(productObjList.get(i).toString());
            productService.updateProductSurplus( productObjList.get(i));
        }

        res.code = 0;
        res.data.put("message","购买成功");

        productObjList.clear();
        cartObjList.clear();

        return res;
    }

    @RequestMapping("/test")
    @ResponseBody
    public Object Test(@RequestHeader("token") String token, @RequestBody JSONObject jsonObj) {
        //        创建响应类
        Response res = new Response();

        Integer user_id = JWTUtils.getUserId(token);
        //读取json里的信息
        String test = jsonObj.getString("test");

        res.data.put("id", user_id);
        res.data.put("info", test);
        res.code = 0;
        return res;
    }
}
