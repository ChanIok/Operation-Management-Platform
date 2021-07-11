package com.hrm.controller;


import com.alibaba.fastjson.JSONObject;
import com.hrm.constant.MessageConstant;
import com.hrm.entry.Response;
import com.hrm.pojo.Login;
import com.hrm.service.LoginService;
import com.hrm.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public Object login(@RequestBody JSONObject jsonObj) {

//        创建响应类
        Response res = new Response();
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

            res.data.put("token", token);
            res.data.put("permission", byPermission);
            res.data.put("message", MessageConstant.LOGIN_SUCCESS);

//            登录成功，返回状态码:0
            res.code = 0;
            return res;
        }
        res.data.put("message", MessageConstant.LOGIN_FAILURE);
//        登录失败，返回状态码:2
        res.code = 2;
        return res;
    }

    @RequestMapping("/findAll")
    public List<Login> findAll() {
        return loginService.findAll();
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
