package com.hrm.service;

import com.hrm.pojo.Tradeex;

public interface TradetrService {

    //在管理端的交易流水表中添加用户信息
    int insertTrade(Tradeex tradeex);
}
