package com.hrm.dao;



public interface UserDao {

    //查找用户信息
    Object findUserInfoById(int userId);

    //查找个人信息需要的
    Object findUserById(int user_id);
}
