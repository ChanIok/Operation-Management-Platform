package com.hrm.service;

import com.hrm.pojo.Advantage;

import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 18:39
 */
public interface AdvantageService {

    List<Advantage> listAdvantagesByProductId(int product_id);

    //增加advantage的信息
    int insertAdvantage(Advantage advantage);

    //删除advantage的信息
    int deleteAdvantage(Advantage advantage);

    //修改advantage的信息
    int updateAdvantage(Advantage advantage);
}
