package com.hrm.controller;

import com.alibaba.fastjson.JSONObject;
import com.hrm.entry.Response;
import com.hrm.pojo.Authority;
import com.hrm.service.AuthorityService;
import com.hrm.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 12:18
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @RequestMapping("/find")
    @ResponseBody
    public Object findUserAuthority(@RequestHeader("token") String token) {

        //        创建响应类
        Response res = new Response();

        int user_id = JWTUtils.getUserId(token);

        try {
            Authority authority = authorityService.findAuthorityById(user_id);

            res.code  = 0;
            res.data.put("message","返回产品权限信息成功");
            res.data.put("trolley",authority);

        } catch (Exception e) {

            e.printStackTrace();
            res.code  = 1;
            res.data.put("message","返回产品权限信息失败");
            res.data.put("trolley",null);

        }

        return res;
    }
}
