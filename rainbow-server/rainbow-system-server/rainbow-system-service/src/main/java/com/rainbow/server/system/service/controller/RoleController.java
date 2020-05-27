package com.rainbow.server.system.service.controller;

import com.rainbow.common.core.entity.QueryRequest;
import com.rainbow.common.core.entity.system.Role;
import com.rainbow.common.core.utils.RainbowUtil;
import com.rainbow.server.system.service.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  @Description 角色控制器
 *  @author liuhu
 *  @Date 2020/5/27 11:55
 */
@Api(tags = "系统角色接口")
@RestController
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;


    /**
     * @Description 角色分页
     * @author liuhu
     * @createTime 2020-05-27 11:58:58
     * @param queryRequest
     * @param role
     * @return org.springframework.http.ResponseEntity
     */
    @GetMapping("page")
    @ApiOperation("分页查询")
    public ResponseEntity page(QueryRequest queryRequest, Role role){
        return ResponseEntity.ok(RainbowUtil.buildTableData(roleService.page(queryRequest,role)));
    }

    /**
     * @Description 保存角色
     * @author liuhu
     * @createTime 2020-05-27 13:51:17
     * @param role
     * @return org.springframework.http.ResponseEntity
     */
    @PostMapping
    @ApiOperation("保存角色")
    public ResponseEntity addRole(@RequestBody Role role){
        return ResponseEntity.ok(roleService.addRole(role));
   }

   /**
    * @Description 通过ID得到角色
    * @author liuhu
    * @createTime 2020-05-27 14:16:55
    * @param roleId
    * @return org.springframework.http.ResponseEntity
    */
   @GetMapping("{roleId}")
   @ApiOperation("通过ID得到角色")
   @ApiImplicitParam(name = "roleId",value = "角色ID")
   public ResponseEntity get(@PathVariable("roleId") Long roleId){
         return ResponseEntity.ok(roleService.getRoleById(roleId));
   }

   /**
    * @Description 删除角色
    * @author liuhu
    * @createTime 2020-05-27 14:31:16
    * @param roleIds
    * @return org.springframework.http.ResponseEntity
    */
   @DeleteMapping("{roleIds}")
   @ApiOperation("删除脚色")
   @ApiImplicitParam(name = "roleIds",value = "角色ID集合")
   public  ResponseEntity delete(@PathVariable("roleIds") String roleIds){
       roleService.deleteRole(roleIds);
       return ResponseEntity.ok().build();
   }

}
