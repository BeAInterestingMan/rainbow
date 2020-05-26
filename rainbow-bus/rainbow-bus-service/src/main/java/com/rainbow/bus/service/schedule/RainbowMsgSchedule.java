package com.rainbow.bus.service.schedule;

import com.alibaba.fastjson.JSONObject;
import com.rainbow.bus.api.entity.MsgLog;
import com.rainbow.bus.service.constant.MsgConstant;
import com.rainbow.bus.service.constant.RainbowRabbitConstant;
import com.rainbow.bus.service.service.IMsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *  @Description 消息重新投递定时任务
 *  @author liuhu
 *  @Date 2020/5/26 9:23
 */
@Component
@Slf4j
public class RainbowMsgSchedule {

    @Autowired
    private IMsgLogService msgLogService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @Description 每隔30秒消息重新投递
     * @author liuhu
     * @createTime 2020-05-26 10:05:51
     * @param
     * @return void
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void reSentMsg(){
        log.info("消息重复投递开始----------------");
        // 查询所有的待投递消息
        List<MsgLog> msgLogs = msgLogService.selectDeliverMes();
        if(!CollectionUtils.isEmpty(msgLogs)){
            msgLogs.forEach(msgLog -> {
                String msgId = msgLog.getMsgId().toString();
                // 如果重试次数超过三次  则为失败
                if(msgLog.getTryCount()<= MsgConstant.MsgLogStatus.MAX_TRY_COUNT){
                    // 重试
                    CorrelationData correlationData = new CorrelationData(msgId);
                    rabbitTemplate.convertAndSend(RainbowRabbitConstant.RainbowExchange.EMAIL_EXCHANGE,
                            RainbowRabbitConstant.RainbowRoutingKey.EMAIL_ROUTING_KEY
                            ,JSONObject.toJSONString(msgLog),correlationData);
                    // 次数加一
                    msgLogService.updateTryCount(msgLog.getMsgId());
                }else{
                    // 状态改为失败
                    msgLogService.updateStatus(msgId, MsgConstant.MsgLogStatus.DELIVER_FAIL);
                    log.info("超过最大重试次数, 消息投递失败, msgId: {}", msgId);
                }
            });
        }
    }
}
