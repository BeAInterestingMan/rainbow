package com.rainbow.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.common.core.entity.system.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  @Description 菜单业务层实现类
 *  @author liuhu
 *  @Date 2020/5/15 10:27
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * @Description
     * @author liuhu
     * @createTime 2020-05-15 16:52:26
     * @param username
     * @return java.util.List<com.rainbow.common.core.entity.system.Menu>
     */
    List<Menu> findUserPermissions(String username);
}
