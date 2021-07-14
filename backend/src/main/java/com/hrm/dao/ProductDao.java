package com.hrm.dao;

import com.hrm.pojo.Product;

import java.util.List;

/**
 * @author zjw
 * @package backend
 * @Date 2021/7/13
 * @Time 14:59
 */

public interface ProductDao {

    //根据ID获取产品基本信息
    Product findById(int product_id);

    //更新产品剩余量
    int updateProductSurplus(Product product);


//    根据类型获取该类型所有产品的基本信息
    List<Product> listProductsByType(String type);

}
