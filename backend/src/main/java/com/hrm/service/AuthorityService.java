package com.hrm.service;

import com.hrm.pojo.Authority;

/**
 * @author zjw
 * @package hrm
 * @Date 2021/7/15
 * @Time 11:08
 */
public interface AuthorityService {

    //向产品权限表中增添信息
    int insertAuthority(Authority authority);

    //查询是否该用户是否有相同的产品
    Authority checkAuthority(Authority authority);

    //查找指定用户中产品权限表的信息
    Authority findAuthorityById(int user_id);

    //更新产品权限表中的数据
    int updateAuthority(Authority authority);
}
