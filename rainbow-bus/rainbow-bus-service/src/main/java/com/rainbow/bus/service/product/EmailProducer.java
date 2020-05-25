package com.rainbow.bus.service.product;

import com.alibaba.fastjson.JSONObject;
import com.rainbow.bus.api.entity.MsgLog;
import com.rainbow.bus.api.entity.RainbowMail;
import com.rainbow.bus.service.constant.RainbowRabbitConstant;
import com.rainbow.bus.service.exception.BusException;
import com.rainbow.bus.service.mapper.MsgLogMapper;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 *  @Description
 *  @author liuhu
 *  @Date 2020/5/23 15:29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmailProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MsgLogMapper msgLogMapper;

    /**
     * @Description 发送邮件业务
     * @author liuhu
     * @createTime 2020-05-25 16:03:38
     * @param rainbowMail
     * @return void
     */
    public void send(RainbowMail rainbowMail) {
       try {
           MsgLog msgLog = MsgLog.builder().msg(rainbowMail.getSubject()).status(0).exchange(RainbowRabbitConstant.RainbowExchange.EMAIL_EXCHANGE)
                   .routingKey(RainbowRabbitConstant.RainbowRoutingKey.EMAIL_ROUTING_KEY).createTime(new Date()).build();
           msgLogMapper.insert(msgLog);
           // 消息ID
           CorrelationData correlationData = new CorrelationData(msgLog.getMsgId().toString());
           // 转json
           String json = JSONObject.toJSONString(rainbowMail);
           rabbitTemplate.convertAndSend(RainbowRabbitConstant.RainbowExchange.EMAIL_EXCHANGE,
                   RainbowRabbitConstant.RainbowRoutingKey.EMAIL_ROUTING_KEY,json,correlationData);
       }catch (Exception e){
           e.printStackTrace();
           throw new BusException("发送邮件失败");
       }
    }
}
