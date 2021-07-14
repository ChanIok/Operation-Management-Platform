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

    //根据product_id查找信息
    Product findById(int product_id);

    //更新产品剩余量
    int updateProductSurplus(Product product);

    List<Product> listProductsByType(String type);
}
