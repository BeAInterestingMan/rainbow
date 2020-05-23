package com.rainbow.bus.service.service.impl;

import com.rainbow.bus.api.entity.MsgLog;
import com.rainbow.bus.service.mapper.MsgLogMapper;
import com.rainbow.bus.service.service.IMsgLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *  @Description 消息投递日志
 *  @author liuhu
 *  @Date 2020/5/22 17:41
 */
@Service
public class MsgLogServiceImpl implements IMsgLogService {

    @Autowired
    private MsgLogMapper msgLogMapper;

    @Override
    public void updateStatus(String msgId, Integer delivering) {
        try {
            if(StringUtils.isNotBlank(msgId)){
                MsgLog msgLog = MsgLog.builder().msgId(msgId).status(delivering).build();
                msgLogMapper.updateById(msgLog);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
