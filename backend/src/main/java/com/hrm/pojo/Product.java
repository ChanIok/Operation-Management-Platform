package com.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjw
 * @package backend
 * @Date 2021/7/13
 * @Time 14:55
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int product_id;
    private String product_name;
        private int surplus;
    private String introduction;
    private int price;
    private String type;
    private String img_url;
}
