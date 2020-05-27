package com.rainbow.server.system.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbow.common.core.entity.system.Role;
import org.springframework.stereotype.Repository;

/**
 *  @Description 角色业务层实现类
 *  @author liuhu
 *  @Date 2020/5/15 10:28
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * @Description 分页查询
     * @author liuhu
     * @createTime 2020-05-27 13:19:20
     * @param page
     * @param role
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rainbow.common.core.entity.system.Role>
     */
    IPage<Role> selectRolePage(Page<Role> page, Role role);
}
