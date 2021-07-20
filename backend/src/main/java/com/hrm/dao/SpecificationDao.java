package com.hrm.dao;


import com.hrm.pojo.Page;
import com.hrm.pojo.Specification;

import java.util.List;

public interface SpecificationDao {
    //    获取购买类型的specification
    List<Specification> listSpecificationsByProductId(int product_id);

    //获取体验类型的specification
    List<Specification> listTrialSpecificationsByProductId(int product_id);


    //获取体验类型的specification
    List<Specification> listSpecification(Page page);


    //增加specification的信息
    int insertSpecification(Specification specification);

    //删除specification的信息
    int deleteSpecification(Specification specification);

    //修改specification的信息
    int updateSpecification(Specification specification);

}
