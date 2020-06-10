package com.rainbow.bus.service.listener;

import com.rabbitmq.client.Channel;
import com.rainbow.bus.service.constant.RainbowRabbitConstant;
import com.rainbow.bus.service.consumer.EmailConsumer;
import com.rainbow.bus.service.proxy.BaseConsumer;
import com.rainbow.bus.service.proxy.BaseConsumerProxy;
import com.rainbow.bus.service.service.MsgLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 *  @Description 邮件消息监听器
 *  @author liuhu
 *  @Date 2020/5/23 15:21
 */
@Component
@RequiredArgsConstructor
public class EmailListener {

    private final EmailConsumer emailConsumer;

    private final MsgLogService msgLogService;

    /**
     * @Description 执行逻辑
     * @author liuhu
     * @createTime 2020-05-25 15:33:01
     * @param message
     * @param channel
     * @return void
     */
    @RabbitListener(queues = RainbowRabbitConstant.RainbowQueue.EMAIL_QUEUE)
    public void handler(Message message, Channel channel) throws IOException {
        // 代理
        BaseConsumerProxy proxy = new BaseConsumerProxy(emailConsumer,msgLogService);
        BaseConsumer baseConsumer = (BaseConsumer)proxy.proxy();
        if(null != baseConsumer){
            baseConsumer.consume(message,channel);
        }
    }
}
