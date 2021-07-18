package com.hrm.service.ServiceImpl;

import com.hrm.dao.HistoryDao;
import com.hrm.pojo.ExperienceHistory;
import com.hrm.pojo.TransactionHistory;
import com.hrm.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    @Autowired
    HistoryDao historyDao;

    @Override
    public ArrayList<TransactionHistory> findTransactionHistory() {
        return historyDao.findTransactionHistory();
    }

    @Override
    public ArrayList<ExperienceHistory> findExperienceHistory() {
        return historyDao.findExperienceHistory();
    }
}
