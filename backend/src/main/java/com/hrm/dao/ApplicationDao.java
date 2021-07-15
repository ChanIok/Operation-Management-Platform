package com.hrm.dao;

import com.hrm.pojo.Application;


import java.util.List;

public interface ApplicationDao {


    List<Application> listApplicationsByProductId(int product_id);

    //增加application的信息
    int insertApplication(Application application);

    //删除application的信息
    int deleteApplication(Application application);

    //修改application的信息
    int updateApplication(Application application);
}
