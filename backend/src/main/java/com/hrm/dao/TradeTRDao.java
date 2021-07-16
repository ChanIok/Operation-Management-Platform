package com.hrm.dao;

import com.hrm.pojo.TradeTR;

public interface TradeTRDao {

    //在管理端的交易流水表中添加用户信息
    int insertTrade(TradeTR tradeTR);
}
