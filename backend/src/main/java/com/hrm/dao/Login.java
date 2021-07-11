package com.hrm.dao;

import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/9
 * @Time 15:47
 */
public interface Login {

    List<com.hrm.pojo.Login> findAll();

    void DeletedById(String id);

    void InsertByLogin(com.hrm.pojo.Login loginPojo);

    String findById(String username);

    String findByPermission(String username);
}
