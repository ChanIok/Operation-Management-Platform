package com.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceHistory {
    private String trade_ex_id;
    private Integer user_id;
    private String username;
    private String product_name;
    private String specification_name;
    private Integer number;
    private String experience_time;
}
