package com.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeEX {
    private Integer trade_ex_id;
    private Integer product_id;
    private Integer user_id;
    private Integer specification_id;
    private String product_name;
    private String experience_time;
    private Integer number;
}
