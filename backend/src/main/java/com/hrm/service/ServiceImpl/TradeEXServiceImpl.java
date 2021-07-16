package com.hrm.service.ServiceImpl;

import com.hrm.dao.TradeEXDao;
import com.hrm.pojo.TradeEX;
import com.hrm.service.TradeEXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeEXServiceImpl implements TradeEXService {

    @Autowired
    private TradeEXDao tradeEXDao;

    @Override
    public int insertTrade(TradeEX tradeEX) {
        return tradeEXDao.insertTrade(tradeEX);
    }
}
