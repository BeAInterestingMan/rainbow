package com.rainbow.bus.service.service.impl;

import com.rainbow.bus.api.entity.MsgLog;
import com.rainbow.bus.service.exception.BusException;
import com.rainbow.bus.service.mapper.MsgLogMapper;
import com.rainbow.bus.service.service.MsgLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  @Description 消息投递日志
 *  @author liuhu
 *  @Date 2020/5/22 17:41
 */
@Service
public class MsgLogServiceImpl implements MsgLogService {

    @Autowired
    private MsgLogMapper msgLogMapper;

    @Override
    public void updateStatus(String msgId, Integer delivering) {
        try {
            if(StringUtils.isNotBlank(msgId)){
                MsgLog msgLog = msgLogMapper.selectById(msgId);
                msgLog.setStatus(delivering);
                msgLogMapper.updateById(msgLog);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new BusException("更新消息状态失败");
        }
    }

    @Override
    public MsgLog selectById(String correlationId) {
        MsgLog msgLog =null;
        try {
             msgLog = msgLogMapper.selectById(correlationId);
        }catch (Exception e){
            e.printStackTrace();
            throw new BusException("通过Id查询失败");
        }
        return msgLog;
    }

    @Override
    public List<MsgLog> selectDeliverMes() {
        List<MsgLog> msgLog =null;
        try {
            msgLog = msgLogMapper.selectDeliverMes();
        }catch (Exception e){
            e.printStackTrace();
            throw new BusException("查询待投递失败");
        }
        return msgLog;
    }

    @Override
    public void updateTryCount(Long msgId) {
        try {
            MsgLog msgLog = msgLogMapper.selectById(msgId);
            msgLog.setTryCount(msgLog.getTryCount()+1);
            msgLogMapper.updateById(msgLog);
        }catch (Exception e){
            e.printStackTrace();
            throw new BusException("更新最大投递次数失败");
        }
    }
}
