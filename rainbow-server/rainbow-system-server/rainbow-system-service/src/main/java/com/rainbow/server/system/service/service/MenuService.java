package com.rainbow.server.system.service.service;

import com.rainbow.common.core.entity.system.Menu;

import java.util.List;
import java.util.Map;

/**
 *  @Description 菜单业务层接口
 *  @author liuhu
 *  @Date 2020/5/15 10:15
 */
public interface MenuService {
    /**
     * @Description 获得所有菜单集合  tree形式
     * @author liuhu
     * @createTime 2020-05-19 17:46:27
     * @param
     * @return java.util.List<com.rainbow.common.core.entity.system.Menu>
     */
    List<Menu> getMenuTree();
    /**
     * @Description 新增菜单
     * @author liuhu
     * @createTime 2020-05-20 13:09:38
     * @param
     * @return com.rainbow.common.core.entity.system.Menu
     */
    Menu save(Menu menu);
    /**
     * @Description 删除菜单
     * @author liuhu
     * @createTime 2020-05-21 11:11:38
     * @param menuId
     * @return void
     */
    void delete(long menuId);
    /**
     * @Description
     * @author liuhu
     * @createTime 2020-05-21 13:56:57
     * @param username
     * @return com.rainbow.common.core.entity.system.Menu
     */
    List<Menu> getMenuTreeByUsername(String username);
    /**
     * @Description 构建用户信息
     * @author liuhu
     * @createTime 2020-05-21 17:11:19
     * @param username
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String,Object> getUserInfo(String username);
}
