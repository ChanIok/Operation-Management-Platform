package com.hrm.controller;

import com.alibaba.fastjson.JSONObject;
import com.hrm.entry.Response;
import com.hrm.pojo.Authority;
import com.hrm.pojo.Transaction;
import com.hrm.service.AuthorityService;
import com.hrm.service.TransactionService;
import com.hrm.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 12:42
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/find")
    @ResponseBody
    public Object findUserAuthority(@RequestHeader("token") String token) {

        //        创建响应类
        Response res = new Response();

        int user_id = JWTUtils.getUserId(token);

        try {
            ArrayList<Transaction> transactionList = transactionService.findTransactionById(user_id);

            res.code = 0;
            res.data.put("message","返回历史购买订单成功");
            res.data.put("trolley",transactionList);


        } catch (Exception e) {
            e.printStackTrace();

            res.code = 0;
            res.data.put("message","返回历史购买订单失败");
            res.data.put("trolley",null);

        }

        return res;
    }
}
