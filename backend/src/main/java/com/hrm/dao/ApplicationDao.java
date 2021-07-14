package com.hrm.dao;

import com.hrm.pojo.Application;


import java.util.List;

public interface ApplicationDao {
    List<Application> listApplicationsByProductId(int product_id);
}
