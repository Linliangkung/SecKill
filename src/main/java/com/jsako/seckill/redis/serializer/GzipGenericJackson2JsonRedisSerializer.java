package com.jsako.seckill.redis.serializer;

import com.jsako.seckill.util.CompressUtils;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @Date 2019/3/19
 * @Author LLJ
 * @Description
 */
public class GzipGenericJackson2JsonRedisSerializer extends GenericJackson2JsonRedisSerializer {
    @Override
    public byte[] serialize(Object source) throws SerializationException {
        //调用父类获取json二进制数据
        byte[] srcBytes = super.serialize(source);
        //压缩json二进制数据，存入redis中
        return CompressUtils.compress(srcBytes);
    }

    @Override
    public Object deserialize(byte[] source) throws SerializationException {
        //source为压缩的json二进制数据，解压，返回解析的后的object对象
        return super.deserialize(CompressUtils.decompress(source));
    }


}
