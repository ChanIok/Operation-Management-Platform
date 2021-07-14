package com.hrm.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowShopping {

    private int product_id;
    private int specification_id;
    private String produce_name;
    private String specifications_name;
    private int buy_count;
    private int price;

}
