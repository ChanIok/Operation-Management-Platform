package com.hrm.service;


import com.hrm.pojo.login;

import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/9
 * @Time 15:54
 */
public interface LoginService {
    List<login> findAll();

    void DeletedById(String id);

    String findById(String username);

    void InsertByLogin(login loginPojo);

    String findByPermission(String username);
}
