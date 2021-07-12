package com.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author zjw
 * @package backend
 * @Date 2021/7/12
 * @Time 10:21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private List<Map<String,Integer>> cartList;
    //private Map<String,Integer> map;
}
