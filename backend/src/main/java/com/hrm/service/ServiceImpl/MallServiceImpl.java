package com.hrm.service.ServiceImpl;

import com.hrm.dao.AdvantageDao;
import com.hrm.dao.ApplicationDao;
import com.hrm.dao.ProductDao;

import java.util.List;

import com.hrm.dao.SpecificationDao;
import com.hrm.pojo.Advantage;
import com.hrm.pojo.Application;
import com.hrm.pojo.Product;
import com.hrm.pojo.Specification;
import com.hrm.service.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MallServiceImpl implements MallService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private AdvantageDao advantageDao;
    @Autowired
    private SpecificationDao specificationDao;
    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public List<Product> listProductsByType(String type) {
        return productDao.listProductsByType(type);
    }

    @Override
    public   Product fineProductById(int product_id){
        return  productDao.findById(product_id);
    }
    @Override
    public List<Advantage> listAdvantagesByProductId(int product_id) {
        return advantageDao.listAdvantagesByProductId(product_id);
    }

    @Override
    public List<Application> listApplicationsByProductId(int product_id) {
        return applicationDao.listApplicationsByProductId(product_id);
    }

    @Override
    public List<Specification> listSpecificationsByProductId(int product_id) {
        return specificationDao.listSpecificationsByProductId(product_id);
    }

    @Override
    public List<Specification> listTrialSpecificationsByProductId(int product_id) {
        return specificationDao.listTrialSpecificationsByProductId(product_id);
    }

}