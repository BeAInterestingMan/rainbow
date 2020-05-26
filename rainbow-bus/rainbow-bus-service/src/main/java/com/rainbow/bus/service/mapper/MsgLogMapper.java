package com.rainbow.bus.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.bus.api.entity.MsgLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  @Description 消息投递日志
 *  @author liuhu
 *  @Date 2020/5/22 17:56
 */
@Repository
public interface MsgLogMapper extends BaseMapper<MsgLog> {
    /**
     * @Description 查询待投递消息失败
     * @author liuhu
     * @createTime 2020-05-26 09:33:43
     * @param
     * @return java.util.List<com.rainbow.bus.api.entity.MsgLog>
     */
    List<MsgLog> selectDeliverMes();
}
