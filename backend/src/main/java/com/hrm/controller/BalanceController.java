package com.hrm.controller;

import com.hrm.entry.Response;
import com.hrm.service.BalanceService;
import com.hrm.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 10:26
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @RequestMapping("/check")
    @ResponseBody
    public Object checkBalance(@RequestHeader("token") String token) {

        //        创建响应类
        Response res = new Response();

        //在token里获取用户id
        int user_id = JWTUtils.getUserId(token);

        try {
            int money = balanceService.checkMoneyByID(user_id);

            res.code = 0;

            res.data.put("message","查询余额成功");
            res.data.put("balance",money);

        } catch (Exception e) {

            res.code = 1;
            res.data.put("message","查询余额失败");
            res.data.put("balance",null);
            e.printStackTrace();

        }

        return res;
    }
}
