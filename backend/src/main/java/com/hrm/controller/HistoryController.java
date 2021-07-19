package com.hrm.controller;

import com.alibaba.fastjson.JSONObject;
import com.hrm.entry.Response;
import com.hrm.pojo.Page;
import com.hrm.pojo.Transaction;
import com.hrm.pojo.TransactionHistory;
import com.hrm.pojo.ExperienceHistory;
import com.hrm.service.TransactionHistoryService;
import com.hrm.service.TransactionService;
import com.hrm.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 12:42
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/transaction")
public class HistoryController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionHistoryService transactionHistoryService;

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

    @RequestMapping("/findAllTrHistory")
    @ResponseBody
    public Object findAllTrHistory(@RequestBody JSONObject jsonObj) {
        //        创建响应类
        Response res = new Response();

        //        读取json信息，转换为map
        Map<String, Object> limit = new HashMap<>();

        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            limit.put(entry.getKey(), entry.getValue());
        }

        Page page_num = new Page((Integer) limit.get("page_num_start"),(Integer) limit.get("page_num_end"));

        ArrayList<TransactionHistory> transactionHistory = transactionHistoryService.findTransactionHistory(page_num);

        res.data.put("trHistory",transactionHistory);
        return res;
    }


    @RequestMapping("/findAllExHistory")
    @ResponseBody
    public Object findAllExHistory(@RequestBody JSONObject jsonObj) {
        //        创建响应类
        Response res = new Response();

        //        读取json信息，转换为map
        Map<String, Object> limit = new HashMap<>();

        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            limit.put(entry.getKey(), entry.getValue());
        }

        Page page_num = new Page((Integer) limit.get("page_num_start"),(Integer) limit.get("page_num_end"));


        ArrayList<ExperienceHistory> ExperienceHistory = transactionHistoryService.findExperienceHistory(page_num);

        res.data.put("exHistory",ExperienceHistory);
        return res;
    }

}
