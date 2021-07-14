package com.hrm.service;

import com.hrm.pojo.Product;

import java.util.List;

public interface MallService {
    public List<Product> listProductsByType(String type);
}
