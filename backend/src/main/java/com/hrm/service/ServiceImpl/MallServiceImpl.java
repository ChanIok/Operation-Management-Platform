package com.hrm.service.ServiceImpl;

import com.hrm.dao.ProductDao;
import java.util.List;
import com.hrm.pojo.Product;
import com.hrm.service.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MallServiceImpl implements MallService {
    @Autowired
    private ProductDao productDao;

    @Override
    public   List<Product> listProductsByType(String type) {
        return productDao.listProductsByType(type);
    }
}