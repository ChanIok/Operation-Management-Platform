package com.hrm.service;

import com.hrm.pojo.Specification;

import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 18:54
 */
public interface SpecificationService {
    List<Specification> listSpecificationsByProductId(int product_id);

    //增加specification的信息
    int insertSpecification(Specification specification);

    //删除specification的信息
    int deleteSpecification(Specification specification);

    //修改specification的信息
    int updateSpecification(Specification specification);
}
