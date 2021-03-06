package com.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private Integer user_id;
    private Integer product_id;
    private Integer buy_count;
    private Integer specification_id;
}