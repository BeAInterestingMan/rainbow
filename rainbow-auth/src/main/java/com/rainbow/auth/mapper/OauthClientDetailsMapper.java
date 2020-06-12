package com.rainbow.auth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbow.common.core.entity.system.OauthClientDetails;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *  @Description 客户端信息接口
 *  @author liuhu
 *  @Date 2020/5/28 10:23
 */
@Repository
public interface OauthClientDetailsMapper extends BaseMapper<OauthClientDetails> {
    /**
     * @Description 客户端信息分页
     * @author liuhu
     * @createTime 2020-05-28 10:33:14
     * @param page
     * @param oauthClientDetails
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rainbow.common.core.entity.system.OauthClientDetails>
     */
    IPage<OauthClientDetails> selectOauthClientDetailsPage(@Param("page")Page<OauthClientDetails> page, @Param("oauthClientDetails")OauthClientDetails oauthClientDetails);
}
