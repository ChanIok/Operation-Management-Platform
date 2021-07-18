package com.hrm.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hrm.entry.Response;
import com.hrm.constant.Constant;
import com.hrm.pojo.*;
import com.hrm.service.*;
import com.hrm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hrm.utils.JWTUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;


/**
 * @author zjw
 * @package backend
 * @Date 2021/7/11
 * @Time 23:30
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mall")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BalanceService balanceService;


    @Autowired
    private TradeTRService tradetrService;

    @Autowired
    private AuthorityService authorityService;
    /*
     * 更新购物车
     * */
    @RequestMapping(path = "/cart", method = {RequestMethod.POST})
    @ResponseBody
    public Object updateShoppingCart(@RequestHeader("token") String token, @RequestBody JSONObject jsonObj) {

//        创建响应类
        Response res = new Response();
        int user_id = JWTUtils.getUserId(token);
//        定义购物车对象数组
        ArrayList<Cart> cartObjList = new ArrayList<Cart>();
//        获取商品数组
        JSONArray jsonArray = jsonObj.getJSONArray("trolley");
        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            int product_id = jsonObject.getIntValue("product_id");
            int buy_count = jsonObject.getIntValue("buy_count");
            int specification_id = jsonObject.getIntValue("specification_id");

            cartObjList.add(new Cart(user_id, product_id, buy_count, specification_id));
        }

        try {
            cartService.deleteProductsByUserId(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            res.code = Constant.CODE_UPDATE_SHOPPING_CART;
            res.data.put("message", "更新购物车失败");
        }

        try {
            for (Cart cart : cartObjList) {
                Cart checkCart = cartService.checkCart(cart);
                if (checkCart == null) {
                    cartService.insertCart(cart);
                } else {
                    cartService.updateProduct(cart);
                }
            }
            res.code = Constant.CODE_SUCCESS;
            res.data.put("message", "更新购物车成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.code = Constant.CODE_UPDATE_SHOPPING_CART;
            res.data.put("message", "更新购物车失败");
        }
        return res;
    }


    @RequestMapping(path = "/cart", method = {RequestMethod.GET})
    @ResponseBody
    public Object getShoppingCart(@RequestHeader("token") String token) {

        //创建响应类
        Response res = new Response();
        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);
        ID id = new ID(user_id, null, null, null);
        try {
            ArrayList<ShowShopping> shopping = cartService.entryCart(id);
            res.code = 0;
            res.data.put("message", "返回购物车成功");
            ArrayList<ShowShopping> showShoppingArrayList = new ArrayList<>();
            int sum = 0;    //记录总价格
            for (int i = 0; i < shopping.size(); i++) {
                ShowShopping showObj = shopping.get(i);
                sum = sum + showObj.getBuy_count() * showObj.getPrice();
                showShoppingArrayList.add(showObj);
            }
            res.data.put("trolley", showShoppingArrayList);
            res.data.put("sum", sum);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            res.code = 1;
            res.data.put("message", "返回购物车失败");
        }
        return res;
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Object DeleteFromCart(HttpServletRequest request, @RequestBody ArrayList<Map<String, Integer>> cartList) {
        //        创建响应类
        Response res = new Response();

        String token = request.getHeader("token");

        int user_id = JWTUtils.getUserId(token);

        System.out.println("user_id  " + user_id);

        ArrayList<Cart> cartObjList = new ArrayList<Cart>();

        for (int i = 0; i < cartList.size(); i++) {
            cartObjList.add(new Cart(user_id, null, null, null));
        }

        System.out.println("----------------------------2");

        int controller = 0;
        int index = 0;

        for (Map<String, Integer> map : cartList) {
            for (String key : map.keySet()
            ) {

                if (controller == 2) {
                    index++;
                    controller = 0;
                }

                if (controller == 0)
                    cartObjList.get(index).setProduct_id(map.get(key));
                else if (controller == 1)
                    cartObjList.get(index).setSpecification_id(map.get(key));
                controller++;
            }
        }

        System.out.println("---------------------3");

        try {
            for (int i = 0; i < cartList.size(); i++) {
                int deleteProduct = cartService.deleteProduct(cartObjList.get(i));
                System.out.println("ssssssssssssssssssssssss" + deleteProduct);
            }

            res.code = 0;
            res.data.put("message", "删除成功");

        } catch (Exception e) {
            e.printStackTrace();
            res.code = 1;
            res.data.put("message", "删除失败");
        }

        return res;

    }



    @RequestMapping(path = "/cart/settlement", method = {RequestMethod.GET})
    @ResponseBody
    public Object ClearCart(HttpServletRequest request) {
        //        创建响应类
        Response res = new Response();
        int user_id = JWTUtils.getUserId(request.getHeader("token"));
        ID id = new ID(user_id, null, null, null);

        Integer balanceUser = balanceService.checkUser(user_id);
        if ( balanceUser == 0 || balanceUser == null){
            res.code = 1;
            res.data.put("message","无账户");
            return res;
        }

        int aliPay = 0;     //需要支付的总额
        int userMoney = balanceService.checkMoneyByID(user_id);     //查询用户的余额
        if (userMoney <= 0  ) {
            res.code = 1;
            res.data.put("message", "用户余额不足");
            return res;
        }
        System.out.println("-------------用户余额：  " + userMoney);

        try {
            ArrayList<ShowShopping> shopping = cartService.entryCart(id);

            for (ShowShopping showObj : shopping) {
                aliPay = aliPay + showObj.getBuy_count() * showObj.getPrice();
            }

            if (aliPay > userMoney) {
                res.code = 1;
                res.data.put("message", "用户余额不足");
                return res;
            }

            ArrayList<Transaction> transactionArrayList = new ArrayList<Transaction>();
            ArrayList<TradeTR> tradeTRArrayList = new ArrayList<>();
            ArrayList<Authority> authorityArrayList = new ArrayList<>();

            for (int i = 0; i < shopping.size(); i++) {
                ShowShopping showObj = shopping.get(i);
                //获取用户与购物车相关联表的信息
                int product_id = showObj.getProduct_id();
                int specification_id = showObj.getSpecification_id();
                String product_name = showObj.getProduct_name();
                int price = showObj.getPrice();
                int buy_count = showObj.getBuy_count();

                //获取当前时间
                String currentDatetime = DateUtils.getCurrentDatetime();

                //设置过期时间
                String dueDatetime = DateUtils.setDueDatetime(currentDatetime, buy_count);

                System.out.println("当前时间" + currentDatetime);
                System.out.println("过期时间" + dueDatetime);

                try {
                    //创建用户交易流水信息
                    tradeTRArrayList.add(new TradeTR(null, product_id, user_id, specification_id, product_name, price, currentDatetime, buy_count));

                    System.out.println("管理端交易流水信息" + tradeTRArrayList.get(i).toString());
                } catch (Exception e) {
                    e.printStackTrace();

                    res.code = 1;
                    res.data.put("message","创建tradeex表失败");
                    return res;
                }

                try {
                    //插入交易流水信息信息
                    tradetrService.insertTrade(tradeTRArrayList.get(i));
                } catch (Exception e) {
                    e.printStackTrace();

                    res.code = 1;
                    res.data.put("message","插入tradetr表失败");
                    return res;
                }


                //创建用户交易信息
                try {
                    transactionArrayList.add(new Transaction(tradeTRArrayList.get(i).getTrade_tr_id(), user_id, product_id, specification_id, product_name, dueDatetime, buy_count, currentDatetime));

                    System.out.println("客户端交易流水信息" + transactionArrayList.get(i).toString());
                } catch (Exception e) {
                    e.printStackTrace();

                    res.code = 1;
                    res.data.put("message","transaction为空");
                    return res;

                }

        

                //从用户购物车中删除
                cartService.deleteProduct(new Cart(user_id, product_id, buy_count, specification_id));

                //创建用户产品权限信息
                authorityArrayList.add(new Authority(user_id,product_id,product_name,specification_id,dueDatetime));

         //往产品权限表中添加用户信息

                Authority authority = null;
                try {
                    authority = authorityService.checkAuthority(authorityArrayList.get(i));

                } catch (Exception e) {
                    res.code = 1;
                    res.data.put("message","authority为空");
                    e.printStackTrace();
                    return res;
                }
                //System.out.println("产品权限表" + authority.toString());
                //如果产品权限表中无该条数据，则直接插入表中
                if (authority == null){
                    authorityService.insertAuthority(authorityArrayList.get(i));
                }

                //如果表中有该条数据，则更新到期时间
                else {
                    String s = DateUtils.setDueDatetime(authority.getAuthority_time(), buy_count);
                    authority.setAuthority_time(s);
                    authorityService.updateAuthority(authority);
                }
            }
 

            try {
                balanceService.updateMoneyById(new Balance(user_id, userMoney - aliPay));
            } catch (Exception e) {
                e.printStackTrace();
                res.code = 1;
                res.data.put("message", "更新用户余额失败");
                return res;
            }
            res.code = 0;
            res.data.put("message", "购买成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.code = 1;
            res.data.put("message", "购买失败");
        }
        return res;
    }
}
