package com.hrm.handler;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/9
 * @Time 14:51
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg",ex.getMessage());
        //打印异常信息
        ex.printStackTrace();
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
