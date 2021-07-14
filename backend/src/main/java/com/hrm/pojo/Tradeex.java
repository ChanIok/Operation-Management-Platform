package com.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tradeex {
    private Integer trade_tr_id;
    private Integer product_id;
    private Integer user_id;
    private Integer specification_id;
    private String product_name;
    private Integer price;
    private String transaction_time;
    private Integer number;
}
