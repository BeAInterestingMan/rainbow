package com.rainbow.bus.service.proxy;

import com.rabbitmq.client.Channel;
import com.rainbow.bus.api.entity.MsgLog;
import com.rainbow.bus.service.constant.MsgConstant;
import com.rainbow.bus.service.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Proxy;
import java.util.Map;
/**
 *  @Description 动态代理实现 防止消息重复消费  和消息确认
 *  @author liuhu
 *  @Date 2020/5/23 14:59
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class BaseConsumerProxy {
    private Object target;
    private MsgLogService msgLogService;

    public BaseConsumerProxy(Object target, MsgLogService msgLogService) {
        this.target = target;
        this.msgLogService = msgLogService;
    }

    public Object proxy(){
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        Object emailProxy = Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args) -> {
            Message message = (Message) args[0];
            Channel channel = (Channel) args[1];
            // 消息ID
            String correlationId = getCorrelationId(message);
            if (isConsumed(correlationId)) {
                log.info("重复消费, correlationId: {}", correlationId);
                return null;
            }
            // rabbitMq亚奥知道那一条消息确认被消费 tag就是index标识
            MessageProperties properties = message.getMessageProperties();
            long tag = properties.getDeliveryTag();
            try {
                // 执行真正的业务逻辑
                Object result = method.invoke(target, args);
                // 更改消息状态 为已消费
                msgLogService.updateStatus(correlationId, MsgConstant.MsgLogStatus.CONSUMED_SUCCESS);
                //确认消息被消费
                channel.basicAck(tag, false);
                return result;
            } catch (Exception e) {
                log.error("消息代理出现异常：", e);
                //deliveryTag:该消息的index,multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
                //requeue：被拒绝的是否重新入队列
                channel.basicNack(tag, false, true);
                return null;
            }
        });
        return emailProxy;
    }
    private String getCorrelationId(Message message) {
        String correlationId = null;
        MessageProperties properties = message.getMessageProperties();
        Map<String, Object> headers = properties.getHeaders();
        for (Map.Entry entry : headers.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (key.equals("spring_returned_message_correlation")) {
                correlationId = value;
            }
        }

        return correlationId;
    }

    /**
     * @Description 判断是否重复消费
     * @author liuhu
     * @createTime 2020-05-23 14:35:15
     * @param correlationId
     * @return boolean
     */
    private boolean isConsumed(String correlationId) {
        MsgLog msgLog = msgLogService.selectById(correlationId);
        if (null == msgLog || msgLog.getStatus().equals(MsgConstant.MsgLogStatus.CONSUMED_SUCCESS)) {
            return true;
        }
        return false;
    }
}
