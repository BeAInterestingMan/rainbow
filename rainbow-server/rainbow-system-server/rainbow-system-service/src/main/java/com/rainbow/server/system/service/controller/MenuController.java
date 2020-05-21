package com.rainbow.server.system.service.controller;


import com.rainbow.common.core.entity.system.Menu;
import com.rainbow.server.system.service.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 *  @Description 菜单资源控制层
 *  @author liuhu
 *  @Date 2020/5/20 10:39
 */
@Api(tags = "系统菜单接口")
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    /**
     * @Description 获取树结构全部菜单
     * @author liuhu
     * @createTime 2020-05-20 10:44:10
     * @param
     * @return org.springframework.http.ResponseEntity
     */
    @ApiOperation("获取树结构全部菜单")
    @GetMapping
    @PreAuthorize("hasAuthority('menu:view')")
    public ResponseEntity getMenu(){
        return ResponseEntity.ok(menuService.getMenuTree());
    }

    /**
     * @Description 获取用户所属树结构全部菜单
     * @author liuhu
     * @createTime 2020-05-20 10:44:10
     * @param
     * @return org.springframework.http.ResponseEntity
     */
    @ApiOperation("获取用户所属树结构全部菜单")
    @GetMapping("{username}")
    @PreAuthorize("hasAuthority('menu:view')")
    public ResponseEntity getMenuTreeByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(menuService.getMenuTreeByUsername(username));
    }

    /**
     * @Description 保存菜单
     * @author liuhu
     * @createTime 2020-05-20 10:44:10
     * @param
     * @return org.springframework.http.ResponseEntity
     */
    @ApiOperation("保存菜单")
    @PostMapping
    @PreAuthorize("hasAuthority('menu:add')")
    public ResponseEntity save(@RequestBody Menu menu){
        return ResponseEntity.ok(menuService.save(menu));
    }


    /**
     * @Description 删除菜单
     * @author liuhu
     * @createTime 2020-05-20 10:44:10
     * @param
     * @return org.springframework.http.ResponseEntity
     */
    @ApiOperation("删除菜单")
    @DeleteMapping("{menuId}")
    @PreAuthorize("hasAuthority('menu:delete')")
    public ResponseEntity delete(@PathVariable("menuId") long menuId){
        menuService.delete(menuId);
        return ResponseEntity.ok().build();
    }


}
