package com.hrm.pojo;

import java.io.Serializable;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/10
 * @Time 19:13
 */
public class User implements Serializable {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
