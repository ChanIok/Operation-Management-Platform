package com.hrm.service.ServiceImpl;

import com.hrm.dao.CartDao;
import com.hrm.pojo.Cart;
import com.hrm.pojo.ID;
import com.hrm.pojo.ShowShopping;
import com.hrm.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author zjw
 * @package backend
 * @Date 2021/7/12
 * @Time 23:41
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Override
    public int addCart(Cart cart) {
        return cartDao.addCart(cart);
    }

    @Override
    public int subCart(Cart cart) {
        return cartDao.subCart(cart);
    }

    @Override
    public int insertCart(Cart cart) {
        return cartDao.insertCart(cart);
    }

    @Override
    public Cart findById(int user_id) {
        return null;
    }


    @Override
    public Cart checkCart(Cart cart) {
        return cartDao.checkCart(cart);
    }

    @Override
    public int checkProductNum(int product_id) {
        return cartDao.checkProductNum(product_id);
    }

    @Override
    public int purchase(Cart cart) {
        return cartDao.purchase(cart);
    }

    @Override
    public int updateProduct(Cart cart) {
        return cartDao.updateProduct(cart);
    }

    @Override
    public int deleteProduct(Cart cart) {
        return cartDao.deleteProduct(cart);
    }


    @Override
    public int deleteProductsByUserId(int user_id) {
        return cartDao.deleteProductsByUserId(user_id);
    }

    @Override
    public ArrayList<ShowShopping> entryCart(ID id) {
        return cartDao.entryCart(id);
    }
}
