package com.hrm.dao;

import com.hrm.pojo.login;

import java.util.List;
import java.util.Map;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/9
 * @Time 15:47
 */
public interface Login {

    List<login> findAll();

    void DeletedById(String id);

    void InsertByLogin(login loginPojo);

    String findById(String username);

    String findByPermission(String username);
}
