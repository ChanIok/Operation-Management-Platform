package com.hrm.dao;

import com.hrm.pojo.Advantage;

import java.util.List;

public interface AdvantageDao {
    List<Advantage> listAdvantagesByProductId(int product_id);

    //增加advantage的信息
    int insertAdvantage(Advantage advantage);

    //删除advantage的信息
    int deleteAdvantage(Advantage advantage);

    //修改advantage的信息
    int updateAdvantage(Advantage advantage);

}
