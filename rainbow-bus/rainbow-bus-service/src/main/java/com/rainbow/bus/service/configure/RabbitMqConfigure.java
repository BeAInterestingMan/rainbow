package com.rainbow.bus.service.configure;

import com.rainbow.bus.service.constant.MsgConstant;
import com.rainbow.bus.service.constant.RainbowRabbitConstant;
import com.rainbow.bus.service.service.MsgLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author liuhu
 * @Description rabbitMq配置类
 * @Date 2020/5/22 14:44
 */
@Configuration
@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class RabbitMqConfigure {

    private final CachingConnectionFactory connectionFactory;

    private final MsgLogService logService;

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * @Description rabbitTemplate 配置
     * @author liuhu
     * @createTime 2020-05-22 18:19:54
     * @param
     * @return org.springframework.amqp.rabbit.core.RabbitTemplate
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        //  消息是否成功发送到Exchange
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            // 成功
            if(ack){
                // 获得消息唯一ID  消息发送时存入
                String msgId = correlationData.getId();
                // 修改消息记录状态为投递成功
                logService.updateStatus(msgId, MsgConstant.MsgLogStatus.DELIVER_SUCCESS);
            }else{
                 // todo 补偿机制
                log.info("消息发送到Exchange失败, {}, cause: {}", correlationData, cause);
            }
        });
        // 触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        rabbitTemplate.setMandatory(true);
        // 消息是否从Exchange路由到Queue, 注意: 这是一个失败回调, 只有消息从Exchange路由到Queue失败才会回调这个方法
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}", exchange, routingKey, replyCode, replyText, message);
        });
        return rabbitTemplate;
    }


    @Bean
    public Queue emailQueue(){
        return new Queue(RainbowRabbitConstant.RainbowQueue.EMAIL_QUEUE,true);
    }

    @Bean
    public DirectExchange emailExchange(){
        return new DirectExchange(RainbowRabbitConstant.RainbowExchange.EMAIL_EXCHANGE,true,false);
    }

    @Bean
    public Binding emailBinding(){
        return BindingBuilder.bind(emailQueue()).to(emailExchange()).with(RainbowRabbitConstant.RainbowRoutingKey.EMAIL_ROUTING_KEY);
    }
}
