package com.rainbow.bus.service.product;

import com.alibaba.fastjson.JSONObject;
import com.rainbow.bus.api.entity.MsgLog;
import com.rainbow.bus.api.entity.RainbowMail;
import com.rainbow.bus.service.constant.MsgConstant;
import com.rainbow.bus.service.constant.RainbowRabbitConstant;
import com.rainbow.bus.service.exception.BusException;
import com.rainbow.bus.service.mapper.MsgLogMapper;
import com.rainbow.bus.service.utils.MessageHelper;
import com.rainbow.common.core.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class EmailProducer {
    private final RabbitTemplate rabbitTemplate;

    private final MsgLogMapper msgLogMapper;

    /**
     * @Description 发送邮件业务
     * @author liuhu
     * @createTime 2020-05-25 16:03:38
     * @param rainbowMail
     * @return void
     */
    public void send(RainbowMail rainbowMail) {
       try {
           MsgLog msgLog = MsgLog.builder().msg(JsonUtil.objToStr(rainbowMail)).status(MsgConstant.MsgLogStatus.DELIVERING).exchange(RainbowRabbitConstant.RainbowExchange.EMAIL_EXCHANGE)
                   .routingKey(RainbowRabbitConstant.RainbowRoutingKey.EMAIL_ROUTING_KEY).createTime(new Date()).build();
           msgLogMapper.insert(msgLog);
           // 消息ID
           CorrelationData correlationData = new CorrelationData(msgLog.getMsgId().toString());
           rabbitTemplate.convertAndSend(RainbowRabbitConstant.RainbowExchange.EMAIL_EXCHANGE,
                   RainbowRabbitConstant.RainbowRoutingKey.EMAIL_ROUTING_KEY,MessageHelper.objToMsg(rainbowMail),correlationData);
       }catch (Exception e){
           e.printStackTrace();
           throw new BusException("发送邮件失败");
       }
    }
}
