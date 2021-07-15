package com.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjw
 * @package backend
 * @Date 2021/7/12
 * @Time 1:08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Integer user_id;
    private String phone_num;
    private String email;
}
