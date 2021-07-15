package com.hrm.service.ServiceImpl;


import com.hrm.dao.ProductDao;
import com.hrm.pojo.Product;
import com.hrm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 18:50
 */

@Service
public class ProductServiceImpl implements ProductService {

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

    @Override
    public List<Product> listProductsByType(String type) {
        return productDao.listProductsByType(type);
    }

    @Override
    public int insertProduct(Product product) {
        return productDao.insertProduct(product);
    }

    @Override
    public int deleteProduct(Product product) {
        return productDao.deleteProduct(product);
    }

    @Override
    public int updateProduct(Product product) {
        return productDao.updateProduct(product);
    }
}
