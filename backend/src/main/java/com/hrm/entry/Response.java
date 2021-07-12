package com.hrm.entry;


import java.util.HashMap;

/**
 * @author ：yp
 * @description : 封装正常返回结果
 * @version: 1.0
 */

/**
 * 封装返回结果
 */

public class Response {
    public int code;//执行结果代码
    public HashMap<String, Object> data = new HashMap<>();

}