package com.hrm.dao;

import com.hrm.pojo.TransactionHistory;
import com.hrm.pojo.ExperienceHistory;

import java.util.ArrayList;

public interface HistoryDao {

    //在交易表中查找所有流水信息
    ArrayList<TransactionHistory> findTransactionHistory();

    ArrayList<ExperienceHistory> findExperienceHistory();

}
