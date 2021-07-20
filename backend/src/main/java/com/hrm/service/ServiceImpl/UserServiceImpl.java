package com.hrm.service.ServiceImpl;


import com.hrm.dao.UserDao;


import com.hrm.pojo.ExperienceHistory;
import com.hrm.pojo.Page;
import com.hrm.pojo.UserList;
import com.hrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public int updateUserInfo(Object userInfo) {
        return userDao.updateUserInfo(userInfo);
    }

    @Override
    public ArrayList<UserList> findUserList(Page page_num) {
        return userDao.findUserList(page_num);
    }

}
