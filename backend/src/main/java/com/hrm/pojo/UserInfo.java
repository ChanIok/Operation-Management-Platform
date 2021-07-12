package com.hrm.pojo;

/**
 * @author zjw
 * @package backend
 * @Date 2021/7/12
 * @Time 1:08
 */
public class UserInfo {
    private int user_id;
    private String phone_num;
    private String email;

    public UserInfo() {
    }

    public UserInfo(int user_id, String phone_num, String email) {
        this.user_id = user_id;
        this.phone_num = phone_num;
        this.email = email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
