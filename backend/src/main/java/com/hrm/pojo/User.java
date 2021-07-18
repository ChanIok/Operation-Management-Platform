package com.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/10
 * @Time 19:13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private int user_id;
    private String username;
    private String password;
    private Integer permission;
    private String salt;
    public String getUsername() {
        return username;
    }
}
