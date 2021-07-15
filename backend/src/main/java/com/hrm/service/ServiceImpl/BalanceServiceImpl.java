package com.hrm.service.ServiceImpl;

import com.hrm.dao.BalanceDao;
import com.hrm.pojo.Balance;
import com.hrm.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zjw
 * @package backend
 * @Date 2021/7/13
 * @Time 16:58
 */

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceDao balanceDao;

    @Override
    public int checkMoneyByID(int user_id) {
        return balanceDao.checkMoneyByID(user_id);
    }

    @Override
    public int updateMoneyById(Balance balance) {
        return balanceDao.updateMoneyById(balance);
    }

    @Override
    public int checkUser(int user_id) {
        return balanceDao.checkUser(user_id);
    }

    @Override
    public int addBalance(int user_id) {
        return balanceDao.addBalance(user_id);
    }
}
