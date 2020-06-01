package com.rainbow.server.system.service.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.common.core.entity.system.RoleMenu;

import java.util.List;

/**
 *  @Description 角色菜单
 *  @author liuhu
 *  @Date 2020/5/26 16:48
 */
public interface RoleMenuService{

   /**
    * @Description 删除角色菜单关联数据
    * @author liuhu
    * @createTime 2020-05-26 16:55:35
    * @param roleIds
    * @return void
    */
    void deleteRoleMenusByRoleId(String[] roleIds);

  /**
   * @Description 删除角色菜单关联数据
   * @author liuhu
   * @createTime 2020-05-26 16:55:41
   * @param menuIds
   * @return void
   */
    void deleteRoleMenusByMenuId(String[] menuIds);

   /**
    * @Description 得到角色菜单
    * @author liuhu
    * @createTime 2020-05-26 16:55:51
    * @param roleId
    * @return java.util.List<com.rainbow.common.core.entity.system.RoleMenu>
    */
    List<RoleMenu> getRoleMenusByRoleId(String roleId);
}
