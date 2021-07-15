package com.hrm.service;

import com.hrm.pojo.Application;

import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 18:47
 */
public interface ApplicationService {

    List<Application> listApplicationsByProductId(int product_id);

    //增加application的信息
    int insertApplication(Application application);

    //删除application的信息
    int deleteApplication(Application application);

    //修改application的信息
    int updateApplication(Application application);

}
