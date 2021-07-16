package com.hrm.dao;

import com.hrm.pojo.Transaction;

import java.util.ArrayList;

public interface TransactionDao {

    //在交易表中添加用户的流水信息
    int insertTransaction(Transaction transaction);

    //在交易表中查找用户的流水信息
    ArrayList<Transaction> findTransactionById(int user_id);
}
