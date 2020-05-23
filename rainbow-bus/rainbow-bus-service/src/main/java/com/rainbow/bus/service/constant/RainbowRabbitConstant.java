package com.rainbow.bus.service.constant;

public interface   RainbowRabbitConstant {
    interface RainbowQueue{
        String EMAIL_QUEUE= "email.queue";
    }
    interface RainbowExchange{
        String EMAIL_EXCHANGE= "email.exchange";
    }
    interface RainbowRoutingKey{
        String EMAIL_ROUTING_KEY= "mail.routing.key";
    }
}
