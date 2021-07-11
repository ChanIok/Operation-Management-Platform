package com.hrm.pojo;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/9
 * @Time 15:51
 */
public class login {
    private String id;
    private String username;
    private String password;
    private Integer permission;

    public login(String id, String username, String password, Integer permission) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.permission = permission;
    }

    public login() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }
}
