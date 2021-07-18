package com.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistory {
    private String trade_tr_id;
    private Integer user_id;
    private String username;
    private String product_name;
    private String specification_name;
    private Integer price;
    private Integer number;
    private String transaction_time;
    private Integer total_price;
}
