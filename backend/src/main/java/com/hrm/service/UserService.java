package com.hrm.service;


import com.hrm.pojo.Page;
import com.hrm.pojo.UserList;

import java.util.ArrayList;

public interface UserService {
    Object findUserInfoById(int user_id);
    int updateUserInfo(Object userInfo);
    ArrayList<UserList> findUserList(Page page_num);
}
