package com.rainbow.server.system.service.service;


import java.util.List;

/**
 *  @Description 用户部门中间表业务层接口
 *  @author liuhu
 *  @Date 2020/5/27 10:32
 */
public interface IUserDataPermissionService {

    /**
     * @Description 通过部门ID删除关联关系
     * @author liuhu
     * @createTime 2020-05-27 10:32:51
     * @param deptIds
     * @return void
     */
    void deleteByDeptIds(List<String> deptIds);

   /**
    * @Description 通过用户ID删除关联关系
    * @author liuhu
    * @createTime 2020-05-27 10:32:43
    * @param userIds
    * @return void
    */
    void deleteByUserIds(String[] userIds);

    /**
     * 通过用户ID查找关联关系
     *
     * @param userId 用户id
     * @return 关联关系
     */
    String findByUserId(String userId);

}
