package com.hrm.service.ServiceImpl;


import com.hrm.pojo.Login;
import com.hrm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/9
 * @Time 15:54
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private com.hrm.dao.Login loginDao;

    @Override
    public List<Login> findAll() {
        return loginDao.findAll();
    }

    @Override
    public void DeletedById(String id) {
        loginDao.DeletedById(id);
    }

    @Override
    public String findById(String username) {
        return loginDao.findById(username);
    }

    @Override
    public void InsertByLogin(Login loginPojo) {
        loginDao.InsertByLogin(loginPojo);
    }

    @Override
    public String findByPermission(String username) {
        return loginDao.findByPermission(username);
    }
}
