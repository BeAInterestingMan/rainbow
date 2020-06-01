package com.rainbow.server.system.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.common.core.entity.QueryRequest;
import com.rainbow.common.core.entity.system.SystemUser;

/**
 *  @Description 用户业务层接口
 *  @author liuhu
 *  @Date 2020/5/15 10:15
 */
public interface UserService {
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
    /**
     * @Description 用户列表分页
     * @author liuhu
     * @createTime 2020-05-26 15:18:40
     * @param queryRequest
     * @param user
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rainbow.common.core.entity.system.SystemUser>
     */
    IPage<SystemUser> userPage(QueryRequest queryRequest, SystemUser user);

    /**
     * @Description 新增用户
     * @author liuhu
     * @createTime 2020-05-26 16:04:26
     * @param user
     * @return com.rainbow.common.core.entity.system.SystemUser
     */
    SystemUser addUser(SystemUser user);

    /**
     * @Description 删除用户
     * @author liuhu
     * @createTime 2020-05-26 17:33:23
     * @param ids 用户
     * @return java.lang.Object
     */
    void deleteUser(String ids);

   /**
    * @Description 修改密码
    * @author liuhu
    * @createTime 2020-05-27 10:29:46
    * @param id
    * @param newPassword
    * @param odlPassword
    * @return void
    */
    void resetPassword(Long id,String newPassword,String odlPassword);

    /**
     * @Description 通过Id得到用户
     * @author liuhu
     * @createTime 2020-05-27 11:32:44
     * @param id
     * @return com.rainbow.common.core.entity.system.SystemUser
     */
    SystemUser getUser(long id);
}
