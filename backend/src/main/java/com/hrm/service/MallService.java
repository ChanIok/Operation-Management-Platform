package com.hrm.service;

import com.hrm.pojo.Advantage;
import com.hrm.pojo.Application;
import com.hrm.pojo.Product;
import com.hrm.pojo.Specification;

import java.util.List;

public interface MallService {
    List<Product> listProductsByType(String type);

    Product fineProductById(int product_id);

    List<Advantage> listAdvantagesByProductId(int product_id);

    List<Application> listApplicationsByProductId(int product_id);

    List<Specification> listSpecificationsByProductId(int product_id);
}
