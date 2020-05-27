package com.rainbow.server.system.service.service;


import java.util.List;

/**
 *  @Description 用户角色
 *  @author liuhu
 *  @Date 2020/5/26 16:48
 */
public interface IUserRoleService{


    /**
     * @Description 删除角色用户管理关系
     * @author liuhu
     * @createTime 2020-05-26 16:54:23
     * @param roleIds
     * @return void
     */
    void deleteUserRolesByRoleId(String[] roleIds);

   /**
    * @Description 删除角色用户管理关系
    * @author liuhu
    * @createTime 2020-05-26 16:54:37
    * @param userIds
    * @return void
    */
    void deleteUserRolesByUserId(String[] userIds);

    /**
     * @Description 查询用户角色
     * @author liuhu
     * @createTime 2020-05-26 16:54:43
     * @param roleIds
     * @return java.util.List<java.lang.String>
     */
    List<String> findUserIdsByRoleId(String[] roleIds);
}
