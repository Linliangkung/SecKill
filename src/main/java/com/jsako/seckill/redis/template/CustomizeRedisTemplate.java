package com.jsako.seckill.redis.template;

import com.jsako.seckill.redis.serializer.GzipGenericJackson2JsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @Date 2019/3/19
 * @Author LLJ
 * @Description
 */
@Component
public class CustomizeRedisTemplate extends RedisTemplate<String, Object> {

    public <T> T customizeOpsForGetValue(String key) {
        return (T) opsForValue().get(key);
    }

    @Override
    @Autowired
    public void setConnectionFactory(RedisConnectionFactory connectionFactory) {
        super.setConnectionFactory(connectionFactory);
    }

    @Override
    public void afterPropertiesSet() {

        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        GzipGenericJackson2JsonRedisSerializer jacksonRedisSerializer = new GzipGenericJackson2JsonRedisSerializer();

        setKeySerializer(stringSerializer);
        setHashKeySerializer(stringSerializer);
        setValueSerializer(jacksonRedisSerializer);
        setHashValueSerializer(jacksonRedisSerializer);

        super.afterPropertiesSet();
    }
}
