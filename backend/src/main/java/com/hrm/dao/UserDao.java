package com.hrm.dao;


import com.hrm.pojo.Page;
import com.hrm.pojo.UserList;

import java.util.ArrayList;

public interface UserDao {
    Object findUserInfoById(int userId);
    int  updateUserInfo(Object userInfo);
    ArrayList<UserList> findUserList(Page page_num);
}
