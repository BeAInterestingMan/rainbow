package com.rainbow.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.common.core.entity.QueryRequest;
import com.rainbow.common.core.entity.system.OauthClientDetails;
import org.apache.ibatis.annotations.Param;

/**
 *  @Description 客户端信息
 *  @author liuhu
 *  @Date 2020/5/28 10:05
 */
public interface OauthClientDetailsService{

    /**
     * @Description 查询（分页）
     * @author liuhu
     * @createTime 2020-05-28 10:04:50
     * @param request
     * @param oauthClientDetails
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rainbow.common.core.entity.system.OauthClientDetails>
     */
    IPage<OauthClientDetails> findOauthClientDetails(QueryRequest request, OauthClientDetails oauthClientDetails);

    /**
     * @Description 根据主键查询
     * @author liuhu
     * @createTime 2020-05-28 10:04:02
     * @param clientId
     * @return com.rainbow.common.core.entity.system.OauthClientDetails
     */
    OauthClientDetails findById(String clientId);

    /**
     * @Description 新增
     * @author liuhu
     * @createTime 2020-05-28 10:04:14
     * @param oauthClientDetails
     * @return void
     */
    OauthClientDetails addOauthClientDetails(OauthClientDetails oauthClientDetails);

   /**
    * @Description 修改
    * @author liuhu
    * @createTime 2020-05-28 10:04:24
    * @param oauthClientDetails
    * @return void
    */
    void updateOauthClientDetails(OauthClientDetails oauthClientDetails);

   /**
    * @Description 删除
    * @author liuhu
    * @createTime 2020-05-28 10:04:36
    * @param clientIds
    * @return void
    */
    void deleteOauthClientDetails(String clientIds);

}
