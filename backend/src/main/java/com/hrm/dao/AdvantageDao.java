package com.hrm.dao;

import com.hrm.pojo.Advantage;

import java.util.List;

public interface AdvantageDao {
    List<Advantage> listAdvantagesByProductId(int product_id);
}
