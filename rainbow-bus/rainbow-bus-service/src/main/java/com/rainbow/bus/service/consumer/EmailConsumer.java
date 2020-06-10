package com.rainbow.bus.service.consumer;


import com.rabbitmq.client.Channel;
import com.rainbow.bus.api.entity.RainbowMail;
import com.rainbow.bus.service.exception.BusException;
import com.rainbow.bus.service.proxy.BaseConsumer;
import com.rainbow.bus.service.utils.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 *  @Description email消费者
 *  @author liuhu
 *  @Date 2020/5/23 14:59
 */
@Component
@RequiredArgsConstructor
public class EmailConsumer implements BaseConsumer {

    private final JavaMailSender javaMailSender;

    @Override
    public void consume(Message message, Channel channel) throws IOException {
        try {
            // 解析邮件对象  发送邮件
            RainbowMail rainbowMail = MessageHelper.msgToObj(message, RainbowMail.class);
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setFrom(rainbowMail.getFromMailAddress());
            simpleMailMessage.setTo(rainbowMail.getToMailAddress());
            simpleMailMessage.setSubject(rainbowMail.getSubject());
            simpleMailMessage.setText(rainbowMail.getText());
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            e.printStackTrace();
            throw new BusException("发送邮件失败");
        }

    }
}
