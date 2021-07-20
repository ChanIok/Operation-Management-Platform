package com.hrm.dao;



public interface UserDao {
    Object findUserInfoById(int userId);
    int  updateUserInfo(Object userInfo);

}
