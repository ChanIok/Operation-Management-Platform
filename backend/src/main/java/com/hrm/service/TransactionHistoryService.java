package com.hrm.service;

import com.hrm.pojo.TransactionHistory;
import com.hrm.pojo.ExperienceHistory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


public interface TransactionHistoryService {

    //在交易表中查找流水信息
    ArrayList<TransactionHistory> findTransactionHistory();

    ArrayList<ExperienceHistory> findExperienceHistory();
}
