package com.hrm.dao;


import com.hrm.pojo.Specification;

import java.util.List;

public interface SpecificationDao {
    List<Specification> listSpecificationsByProductId(int product_id);
}
