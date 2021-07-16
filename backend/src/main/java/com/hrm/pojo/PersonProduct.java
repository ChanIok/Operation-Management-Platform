package com.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/16
 * @Time 16:49
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonProduct {
    private String product_name;
    private String specification_name;
    private String specification_type;
    private String authority_time;
}
