package com.hrm.dao;

import com.hrm.pojo.Tradeex;
import com.hrm.pojo.Transaction;

public interface TransactionDao {

    //在交易表中添加用户的流水信息
    int insertTransaction(Transaction transaction);
}
