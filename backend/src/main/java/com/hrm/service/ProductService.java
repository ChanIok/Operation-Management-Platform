package com.hrm.service;

import com.hrm.pojo.Product;

/**
 * @author zjw
 * @package backend
 * @Date 2021/7/13
 * @Time 15:06
 */
public interface ProductService {

    //根据product_id查找信息
    Product findById(int product_id);

    //更新产品剩余量
    int updateProductSurplus(Product product);
}
