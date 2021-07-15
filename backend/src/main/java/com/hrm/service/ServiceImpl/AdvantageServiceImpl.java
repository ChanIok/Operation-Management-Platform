package com.hrm.service.ServiceImpl;

import com.hrm.dao.AdvantageDao;
import com.hrm.pojo.Advantage;
import com.hrm.service.AdvantageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 18:44
 */

@Service
public class AdvantageServiceImpl implements AdvantageService {


    @Autowired
    private AdvantageDao advantageDao;


    @Override
    public List<Advantage> listAdvantagesByProductId(int product_id) {
        return advantageDao.listAdvantagesByProductId(product_id);
    }

    @Override
    public int insertAdvantage(Advantage advantage) {
        return advantageDao.insertAdvantage(advantage);
    }

    @Override
    public int deleteAdvantage(Advantage advantage) {
        return advantageDao.deleteAdvantage(advantage);
    }

    @Override
    public int updateAdvantage(Advantage advantage) {
        return advantageDao.updateAdvantage(advantage);
    }
}
