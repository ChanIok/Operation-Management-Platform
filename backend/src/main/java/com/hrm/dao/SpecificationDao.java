package com.hrm.dao;


import com.hrm.pojo.Specification;

import java.util.List;

public interface SpecificationDao {
    List<Specification> listSpecificationsByProductId(int product_id);

    //增加specification的信息
    int insertSpecification(Specification specification);

    //删除specification的信息
    int deleteSpecification(Specification specification);

    //修改specification的信息
    int updateSpecification(Specification specification);

}
