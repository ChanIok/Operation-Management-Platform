package com.hrm.service.ServiceImpl;

import com.hrm.dao.TradetrDao;
import com.hrm.pojo.Tradeex;
import com.hrm.service.TradetrService;
import com.hrm.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradetrServiceImpl implements TradetrService {

    @Autowired
    private TradetrDao tradetrDao;

    @Override
    public int insertTrade(Tradeex tradeex) {
        return tradetrDao.insertTrade(tradeex);
    }
}
