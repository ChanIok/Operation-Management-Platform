package com.hrm.service.ServiceImpl;

import com.hrm.dao.TradeTRDao;
import com.hrm.pojo.TradeTR;
import com.hrm.service.TradeTRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeTRServiceImpl implements TradeTRService {

    @Autowired
    private TradeTRDao tradetrDao;

    @Override
    public int insertTrade(TradeTR tradeTR) {
        return tradetrDao.insertTrade(tradeTR);
    }
}
