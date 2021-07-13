package com.hrm.service.ServiceImpl;

import com.hrm.dao.ProductDao;
import com.hrm.pojo.Product;
import com.hrm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zjw
 * @package backend
 * @Date 2021/7/13
 * @Time 15:07
 */

@Service
public class ProductImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findById(int product_id) {
        return productDao.findById(product_id);
    }

    @Override
    public int updateProductSurplus(Product product) {
        return productDao.updateProductSurplus(product);
    }
}
