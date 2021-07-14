package com.hrm.service;

import com.hrm.pojo.Tradeex;
import com.hrm.pojo.Transaction;

public interface TransactionService {
    //在交易表中添加用户的流水信息
    int insertTransaction(Transaction transaction);
}
