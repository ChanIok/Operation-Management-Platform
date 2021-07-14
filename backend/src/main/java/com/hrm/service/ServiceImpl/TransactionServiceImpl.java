package com.hrm.service.ServiceImpl;

import com.hrm.dao.TransactionDao;
import com.hrm.pojo.Tradeex;
import com.hrm.pojo.Transaction;
import com.hrm.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDao transactionDao;


    @Override
    public int insertTransaction(Transaction transaction) {
        return transactionDao.insertTransaction(transaction);
    }
}
