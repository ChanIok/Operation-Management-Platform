package com.hrm.dao;


import com.hrm.pojo.TradeEX;

public interface TradeEXDao {

    //在管理端的交易流水表中添加用户信息
    int insertTrade(TradeEX tradeEX);
}
