package com.hrm.service.ServiceImpl;


import com.hrm.dao.AuthDao;
import com.hrm.pojo.User;
import com.hrm.pojo.UserInfo;
import com.hrm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/9
 * @Time 15:54
 */

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDao authDao;


    @Override
    public String findById(String username) {
        return authDao.findById(username);
    }


    @Override
    public int getPermissionByUsername(String username) {
        return authDao.getPermissionByUsername(username);
    }

    @Override
    public int registerUser(User user) {
        return authDao.registerUser(user);
    }

    @Override
    public int findIdByName(String username) {
        return authDao.findIdbyName(username);
    }

    @Override
    public int setUserInfo(UserInfo userInfo) {
        return authDao.setUserInfo(userInfo);
    }

    @Override
    public Map<String, String> getPasswordAndSaltByUsername(String username) {
        return authDao.getPasswordAndSaltByUsername(username);
    }

}
