package com.rainbow.common.redis.configure;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rainbow.common.redis.service.RedisService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 *  @Description redis 配置类 ConditionalOnProperty  当前仅当 enable为true时开启注入  如果没有配置该属性默认为true
 *  @author liuhu
 *  @Date 2020/5/11 17:41
 */
@ConditionalOnProperty(value = "rainbow.lettuce.redis.enable",havingValue = "true",matchIfMissing = true)
public class LettuceRedisAutoConfigure {

     /**
      * @Description 配置redis序列化方式
      * @author liuhu
      * @createTime 2020-05-11 17:45:44
      * @param factory
      * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.Object>
      */
    @Bean(name = "redisTemplate")
    @ConditionalOnClass(RedisOperations.class)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(mapper);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    /**
     * @Description 注入redis工具类
     * @author liuhu
     * @createTime 2020-05-11 18:16:02
     * @param
     * @return com.rainbow.common.redis.service.RedisService
     */
    @Bean
    public RedisService redisService(){
        return new RedisService();
    }
}
