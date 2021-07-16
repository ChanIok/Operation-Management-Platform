package com.hrm.service.ServiceImpl;

import com.hrm.dao.AuthorityDao;
import com.hrm.pojo.Authority;
import com.hrm.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 11:09
 */

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public int insertAuthority(Authority authority) {
        return authorityDao.insertAuthority(authority);
    }

    @Override
    public Authority checkAuthority(Authority authority) {
        return authorityDao.checkAuthority(authority);
    }

    @Override
    public Authority findAuthorityById(int user_id) {
        return authorityDao.findAuthorityById(user_id);
    }

    @Override
    public int updateAuthority(Authority authority) {
        return authorityDao.updateAuthority(authority);
    }
}
