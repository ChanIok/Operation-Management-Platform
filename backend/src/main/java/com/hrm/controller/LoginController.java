package com.hrm.controller;


import com.hrm.constant.Constant;
import com.hrm.constant.MessageConstant;
import com.hrm.entry.Result;
import com.hrm.pojo.User;
import com.hrm.pojo.login;
import com.hrm.service.LoginService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/9
 * @Time 15:58
 */

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @RequestMapping("/register")
    @ResponseBody
    public Result check(HttpServletRequest request, HttpServletResponse response){

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String byId = loginService.findById(username);

        if (byId.equals(password)){

            User user = new User(username, password);

            Cookie cookie1 = new Cookie("username",username);
            Cookie cookie2 = new Cookie("password", password);

            response.addCookie(cookie1);
            response.addCookie(cookie2);

            //3. 将loginUser存储到session中
            request.getSession().setAttribute(Constant.LOGIN_USER,user);

            String byPermission = loginService.findByPermission(username);
            System.out.println(byPermission);

            return new Result(true,MessageConstant.LOGIN_SUCCESS,byPermission);
        }

        return new Result(false,MessageConstant.LOGIN_FAILURE,null);
    }

    @DeleteMapping("/{id}")
    public void  DeletedById(@PathVariable("id")  String id){
        System.out.println("删除成功");
        loginService.DeletedById(id);
    }

    @PostMapping("/{id}")
    public void insert(@PathVariable("id")  String id, @RequestBody login loginPojo){
        loginService.InsertByLogin(loginPojo);
    }
}
