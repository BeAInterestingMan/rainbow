package com.rainbow.server.system.service.service;

import com.rainbow.common.core.entity.system.Menu;

import java.util.List;

/**
 *  @Description 菜单业务层接口
 *  @author liuhu
 *  @Date 2020/5/15 10:15
 */
public interface IMenuService {
    /**
     * @Description 获得所有菜单集合  tree形式
     * @author liuhu
     * @createTime 2020-05-19 17:46:27
     * @param
     * @return java.util.List<com.rainbow.common.core.entity.system.Menu>
     */
    List<Menu> getMenuTree();
}
