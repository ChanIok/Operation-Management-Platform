package com.hrm.service;

import com.hrm.pojo.TradeEX;


public interface TradeEXService {

    //在管理端的体验流水表中添加用户信息
    int insertTrade(TradeEX tradeEX);
}
