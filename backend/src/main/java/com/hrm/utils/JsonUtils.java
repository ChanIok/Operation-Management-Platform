package com.hrm.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/11
 * @Time 10:47
 */
public class JsonUtils {

    public static JSONObject MapToJson(Map map){
        return new JSONObject(map);
    }
}
