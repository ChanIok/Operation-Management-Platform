package com.hrm.service;


import com.hrm.pojo.Login;

import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/9
 * @Time 15:54
 */
public interface LoginService {
    List<Login> findAll();

    void DeletedById(String id);

    String findById(String username);

    void InsertByLogin(Login loginPojo);

    String findByPermission(String username);
}
