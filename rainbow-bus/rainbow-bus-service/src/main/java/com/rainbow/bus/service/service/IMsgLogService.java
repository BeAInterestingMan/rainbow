package com.rainbow.bus.service.service;


import com.rainbow.bus.api.entity.MsgLog;

/**
 *  @Description 消息投递日志
 *  @author liuhu
 *  @Date 2020/5/22 17:40
 */

public interface IMsgLogService{
   /**
    * @Description 修改消息状态
    * @author liuhu
    * @createTime 2020-05-22 18:10:07
    * @param msgId
    * @param delivering
    * @return void
    */
    void updateStatus(String msgId, Integer delivering);
    /**
     * @Description 根据ID查询
     * @author liuhu
     * @createTime 2020-05-23 14:41:05
     * @param correlationId
     * @return com.rainbow.bus.api.entity.MsgLog
     */
    MsgLog selectById(String correlationId);
}
