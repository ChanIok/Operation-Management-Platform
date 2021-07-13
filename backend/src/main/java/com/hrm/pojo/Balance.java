package com.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjw
 * @package backend
 * @Date 2021/7/13
 * @Time 15:12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
    private int user_id;
    private int user_balance;
}
