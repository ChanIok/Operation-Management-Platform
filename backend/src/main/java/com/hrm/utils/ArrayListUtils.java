package com.hrm.utils;

import com.hrm.pojo.Cart;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author zjw
 * @package backend
 * @Date 2021/7/14
 * @Time 10:56
 */
public class ArrayListUtils {

    public  static ArrayList<Cart> initArrayList(ArrayList<Map<String,Integer>> cartList, int user_id, int size){

        ArrayList<Cart> cartObjList = new ArrayList<Cart>();

        for (int i = 0; i < cartList.size(); i++) {
            cartObjList.add(new Cart(user_id,null,null,null));
        }

        System.out.println("----------------------------2");

        int controller = 0;
        int index = 0;

        for (Map<String,Integer> map: cartList) {
            for (String key: map.keySet()
            ) {

                if (controller == size) {
                    index++;
                    controller = 0;
                }

                if (controller == 0)
                    cartObjList.get(index).setProduct_id(map.get(key));
                else if (controller == 1)
                    cartObjList.get(index).setBuy_count(map.get(key));
                else if (controller == 2)
                    cartObjList.get(index).setSpecification_id(map.get(key));
                controller++;
            }
        }

        return cartObjList;
    }


}
