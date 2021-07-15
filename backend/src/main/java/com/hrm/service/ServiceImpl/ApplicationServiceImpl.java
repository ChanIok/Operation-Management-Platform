package com.hrm.service.ServiceImpl;

import com.hrm.dao.ApplicationDao;
import com.hrm.pojo.Application;
import com.hrm.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 18:48
 */

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public List<Application> listApplicationsByProductId(int product_id) {
        return applicationDao.listApplicationsByProductId(product_id);
    }

    @Override
    public int insertApplication(Application application) {
        return applicationDao.insertApplication(application);
    }

    @Override
    public int deleteApplication(Application application) {
        return applicationDao.deleteApplication(application);
    }

    @Override
    public int updateApplication(Application application) {
        return applicationDao.updateApplication(application);
    }
}
