package com.hrm.controller;


import com.alibaba.fastjson.JSONObject;
import com.hrm.constant.Constant;
import com.hrm.constant.MessageConstant;
import com.hrm.entry.Result;
import com.hrm.pojo.User;
import com.hrm.pojo.login;
import com.hrm.service.LoginService;
import com.hrm.utils.JWTUtils;
import com.hrm.utils.JsonUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/9
 * @Time 15:58
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @RequestMapping("/login")
    @ResponseBody
    public Object check(@RequestBody JSONObject jsonObj) {

        HashMap<String, Object> data = new HashMap<>();
        HashMap<String, Object> res = new HashMap<>();
        HashMap<String, String> payload = new HashMap<>();

        //读取json里的信息
        String username = jsonObj.getString("username");
        String password = jsonObj.getString("password");

        String byId = loginService.findById(username);

        if (byId.equals(password)) {


            String byPermission = loginService.findByPermission(username);
            System.out.println(byPermission);

            payload.put("username", username);
            String token = JWTUtils.getToken(payload);


            data.put("token", token);
            data.put("permission", byPermission);
            data.put("message", MessageConstant.LOGIN_SUCCESS);

            res.put("code", 0);
            res.put("data", data);
            return res;
        }
        data.put("message", MessageConstant.LOGIN_FAILURE);
        res.put("code", 2);
        res.put("data", data);
        return res;
    }

    @RequestMapping("/findAll")
    public List<login> findAll() {
        return loginService.findAll();
    }

    @RequestMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
       /* //1.销毁session
        request.getSession().invalidate();*/

        return new Result(true, MessageConstant.LOGIN_OUT);
    }

    @DeleteMapping("/{id}")
    public void DeletedById(@PathVariable("id") String id) {
        System.out.println("删除成功");
        loginService.DeletedById(id);
    }

    /*
     * 测试token
     * */
    public Map<String, Object> test() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("flag", true);
        map.put("message", "请求成功");
        return map;
    }

}
