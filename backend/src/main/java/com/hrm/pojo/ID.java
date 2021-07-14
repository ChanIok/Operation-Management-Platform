package com.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ID {

    private int user_id;
    private int product_id;
    private int specifications_id;
    private int trade_id;

    public ID(int user_id, Integer integer, Object o, Object o1) {
        this.user_id = user_id;
    }
}
