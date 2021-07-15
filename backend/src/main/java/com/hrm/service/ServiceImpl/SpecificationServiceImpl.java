package com.hrm.service.ServiceImpl;

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
    private SpecificationService specificationService;

    @Override
    public List<Specification> listSpecificationsByProductId(int product_id) {
        return specificationService.listSpecificationsByProductId(product_id);
    }

    @Override
    public int insertSpecification(Specification specification) {
        return specificationService.insertSpecification(specification);
    }

    @Override
    public int deleteSpecification(Specification specification) {
        return specificationService.deleteSpecification(specification);
    }

    @Override
    public int updateSpecification(Specification specification) {
        return specificationService.updateSpecification(specification);
    }
}
