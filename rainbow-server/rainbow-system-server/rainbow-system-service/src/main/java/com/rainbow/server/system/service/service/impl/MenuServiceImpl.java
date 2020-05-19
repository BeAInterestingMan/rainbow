package com.rainbow.server.system.service.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rainbow.common.core.entity.system.Menu;
import com.rainbow.server.system.service.mapper.MenuMapper;
import com.rainbow.server.system.service.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  @Description 菜单业务层实现类
 *  @author liuhu
 *  @Date 2020/5/15 10:23
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuTree() {
        List<Menu> rootMenu = menuMapper.selectList(new QueryWrapper<Menu>().orderByDesc("create_time"));
        List<Menu> menuTree=null;
        // 得到顶级菜单  递归子菜单
        if(!CollectionUtils.isEmpty(rootMenu)){
            menuTree = rootMenu.stream().filter(menu -> Menu.TYPE_MENU.equals(menu.getType()) && Menu.TOP_MENU_ID == menu.getParentId())
                    .map(menu -> {
                        menu.setChildMenus(getChildren(menu, rootMenu));
                        return menu;
                    }).collect(Collectors.toList());
        }
        return menuTree;
    }

    /**
     * @Description 递归得到子菜单
     * @author liuhu
     * @createTime 2020-05-19 18:38:38
     * @param parentMenu 父菜单
     * @param rootMenu 元数据集合
     * @return java.util.List<com.rainbow.common.core.entity.system.Menu>
     */
    public List<Menu> getChildren(Menu parentMenu, List<Menu> rootMenu) {
     return rootMenu.stream().filter(menu -> Menu.TYPE_MENU.equals(menu.getType()) && parentMenu.getMenuId() == menu.getParentId())
             .map(menu -> {
                        menu.setChildMenus(getChildren(menu,rootMenu));
                        return menu;
                        }).sorted(Comparator.comparing(Menu::getCreateTime)).collect(Collectors.toList());
    }
}
