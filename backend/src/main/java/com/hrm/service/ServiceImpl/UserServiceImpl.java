package com.hrm.service.ServiceImpl;


import com.hrm.dao.UserDao;


import com.hrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Object findUserInfoById(int user_id) {
        return userDao.findUserInfoById(user_id);
    }

    @Override
    public Object findUserById(int user_id) {
        return userDao.findUserById(user_id);
    }


}
