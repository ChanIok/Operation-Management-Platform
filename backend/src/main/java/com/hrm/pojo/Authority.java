package com.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 11:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    private Integer user_id;
    private Integer product_id;
    private String product_name;
    private Integer specification_id;
    private String authority_time;
}
