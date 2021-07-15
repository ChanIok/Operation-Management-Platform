package com.hrm.service;




public interface UserService {
    Object findUserInfoById(int user_id);
    int updateUserInfo(Object userInfo);
}
