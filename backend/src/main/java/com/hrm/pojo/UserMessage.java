package com.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 19:46
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessage {
    private Integer user_id;
    private String username;
    private String phone_num;
    private String email;
}
