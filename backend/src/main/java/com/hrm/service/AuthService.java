package com.hrm.service;



import com.hrm.pojo.User;
import com.hrm.pojo.UserInfo;

import java.util.Map;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/9
 * @Time 15:54
 */
public interface AuthService {

    String findById(String username);

    String findByPermission(String username);

    int registerUser(User user);

    int findIdByName(String username);

    int setUserInfo(UserInfo userInfo);

    Map<String,String> getPasswordAndSaltByUsername(String username);
}
