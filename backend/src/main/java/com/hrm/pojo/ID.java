package com.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ID {

    private Integer user_id;
    private Integer product_id;
    private Integer specification_id;
    private Integer trade_id;
}
