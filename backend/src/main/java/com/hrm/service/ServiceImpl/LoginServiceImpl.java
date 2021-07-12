package com.hrm.service.ServiceImpl;



import com.hrm.dao.LoginDao;
import com.hrm.pojo.Login;
import com.hrm.pojo.UserInfo;
import com.hrm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/9
 * @Time 15:54
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;


    @Override
    public String findById(String username) {
        return loginDao.findById(username);
    }


    @Override
    public String findByPermission(String username) {
        return loginDao.findByPermission(username);
    }

    @Override
    public int registerUser(Login login) {
       return loginDao.registerUser(login);
    }

    @Override
    public int findIdbyName(String username) {
        return loginDao.findIdbyName(username);
    }

    @Override
    public int setUserInfo(UserInfo userInfo) {
        return loginDao.setUserInfo(userInfo);
    }
}
