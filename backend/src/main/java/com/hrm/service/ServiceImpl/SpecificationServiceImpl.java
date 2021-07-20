package com.hrm.service.ServiceImpl;

import com.hrm.dao.SpecificationDao;
import com.hrm.pojo.Page;
import com.hrm.pojo.Specification;
import com.hrm.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 18:55
 */

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationDao specificationDao;

    @Override
    public List<Specification> listSpecificationsByProductId(int product_id) {
        return specificationDao.listSpecificationsByProductId(product_id);
    }

    @Override
    public List<Specification> listSpecification(Page page) {
        return specificationDao.listSpecification( page);
    }


    @Override
    public int insertSpecification(Specification specification) {
        return specificationDao.insertSpecification(specification);
    }

    @Override
    public int deleteSpecification(Specification specification) {
        return specificationDao.deleteSpecification(specification);
    }

    @Override
    public int updateSpecification(Specification specification) {
        return specificationDao.updateSpecification(specification);
    }
}
