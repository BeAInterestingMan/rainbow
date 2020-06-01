package com.rainbow.server.system.service.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rainbow.common.core.entity.system.Menu;
import com.rainbow.server.system.service.exception.SystemException;
import com.rainbow.server.system.service.mapper.MenuMapper;
import com.rainbow.server.system.service.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  @Description 菜单业务层实现类
 *  @author liuhu
 *  @Date 2020/5/15 10:23
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuTree() {
       try {
           List<Menu> rootMenu = menuMapper.selectList(new QueryWrapper<Menu>().orderByDesc("create_time"));
           // 得到顶级菜单  递归子菜单
           return buildTree(rootMenu);
       }catch (Exception e){
           e.printStackTrace();
           throw new SystemException("获取树菜单失败");
       }
    }

    @Override
    public Menu save(Menu menu) {
       try {
           if(null != menu.getMenuId()){
               menuMapper.updateById(menu);
           }else{
               menuMapper.insert(menu);
           }
       }catch (Exception e){
           e.printStackTrace();
           throw new SystemException("保存菜单失败");
       }
        return menu;
    }

    @Override
    public void delete(long menuId) {
        try {
            Menu menu = menuMapper.selectById(menuId);
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
            List<Menu> menus = menuMapper.selectList(queryWrapper.eq("PARENT_ID", menu.getMenuId()));
            if(!CollectionUtils.isEmpty(menus)){
                throw new SystemException("该菜单有子菜单不允许删除");
            }
            menuMapper.deleteById(menuId);
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("删除菜单失败");
        }
    }

    @Override
    public List<Menu> getMenuTreeByUsername(String username) {
        try {
            List<Menu> menus = menuMapper.selectUserMenu(username);
            return buildTree(menus);
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("构建用户菜单失败");
        }
    }

    @Override
    public Map<String, Object> getUserInfo(String username) {
        Map<String, Object> map = new HashMap<>();
     try {
         List<Menu> menus = getMenuTreeByUsername(username);
         List<Menu> userPermissions = menuMapper.findUserPermissions(username);
         List<String> perms = userPermissions.stream().map(Menu::getPerms).collect(Collectors.toList());
         map.put("permissions",perms);
         map.put("routes",menus);
     }catch (Exception e){
         e.printStackTrace();
         throw new SystemException("获取用户权限信息失败");
     }
        return map;
    }

    /**
     * @Description 构建树形菜单
     * @author liuhu
     * @createTime 2020-05-21 17:07:28
     * @param rootMenu
     * @return java.util.List<com.rainbow.common.core.entity.system.Menu>
     */
    public List<Menu> buildTree(List<Menu> rootMenu){
        List<Menu> menuTree=null;
        try {
            if(!CollectionUtils.isEmpty(rootMenu)){
                menuTree = rootMenu.stream().filter(menu -> Menu.TYPE_MENU.equals(menu.getType()) && Menu.TOP_MENU_ID == menu.getParentId())
                        .map(menu -> {
                            menu.setChildMenus(getChildren(menu, rootMenu));
                            return menu;
                        }).collect(Collectors.toList());
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("构建树菜单失败");
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
