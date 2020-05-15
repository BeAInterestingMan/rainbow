package com.rainbow.auth.service;

import com.rainbow.common.core.entity.system.SystemUser;

import java.util.List;

/**
 *  @Description 用户业务层接口
 *  @author liuhu
 *  @Date 2020/5/15 10:15
 */
public interface IUserService {
    /**
     * @Description 得到用户
     * @author liuhu
     * @createTime 2020-05-15 16:49:15
     * @param username
     * @return com.rainbow.common.core.entity.system.SystemUser
     */
    SystemUser selectUserByUsername(String username);
    /**
     * @Description 得到用户权限
     * @author liuhu
     * @createTime 2020-05-15 16:49:29
     * @param username
     * @return void
     */
    String findUserPermissions(String username);
}
