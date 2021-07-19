package com.hrm.service.ServiceImpl;

import com.hrm.dao.HistoryDao;
import com.hrm.pojo.ExperienceHistory;
import com.hrm.pojo.TransactionHistory;
import com.hrm.pojo.Page;
import com.hrm.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    @Autowired
    HistoryDao historyDao;

    @Override
    public ArrayList<TransactionHistory> findTransactionHistory(Page page_num) {
        return historyDao.findTransactionHistory(page_num);
    }

    @Override
    public ArrayList<ExperienceHistory> findExperienceHistory(Page page_num) {
        return historyDao.findExperienceHistory(page_num);
    }
}
