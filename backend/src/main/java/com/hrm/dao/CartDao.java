package com.hrm.dao;

import com.hrm.pojo.Cart;

/**
 * @author zjw
 * @package backend
 * @Date 2021/7/12
 * @Time 10:18
 */
public interface CartDao {
    //添加商品(已存在)
    int addCart(Cart cart);

    //删减商品(已存在)
    int subCart(Cart cart);

    //添加商品（不存在）
    int insertCart(Cart cart);

    //查找是否用户购物车存在对应商品
    Cart checkCart(Cart cart);

    //用户购买时检查产品数量
    int checkProductNum(int product_id);

    //购买对应商品
    int purchase(Cart cart);

    //更新商品
    int updateProduct(Cart cart);
}