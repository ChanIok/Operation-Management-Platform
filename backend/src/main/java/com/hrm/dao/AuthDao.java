package com.hrm.dao;


import com.hrm.pojo.User;
import com.hrm.pojo.UserInfo;

import java.util.Map;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/9
 * @Time 15:47
 */
public interface AuthDao {

    String findById(String username);

    int getPermissionByUsername(String username);

    int registerUser(User user);

    int findIdbyName(String username);

    int setUserInfo(UserInfo userInfo);

    Map<String,String> getPasswordAndSaltByUsername(String username);
}
