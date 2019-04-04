package com.jsako.seckill.component;

import com.jsako.seckill.redis.template.CustomizeRedisTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Date 2019/3/19
 * @Author LLJ
 * @Description
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CustomizeRedisTemplate redisTemplate;

    @Test
    public void testStringRedisTemp(){
        stringRedisTemplate.opsForValue().set("test","mytest");
    }

    @Test
    public void testStringRedisTempGet(){
        System.out.println(stringRedisTemplate.opsForValue().get("testObject"));
    }

    @Test
    public void testObjectRedisTemp(){
        redisTemplate.opsForValue().set("testObject", new User("llj",25));

    }

    @Test
    public void testObjectRedisTempGet(){
        User user = redisTemplate.customizeOpsForGetValue("testObject");
        System.out.println(user);
    }

    @Test
    public void testGetSet(){
        Object andSet = redisTemplate.opsForValue().getAndSet("testObject", new User("llj", 24));
        System.out.println(andSet);
    }

    public static class User{

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        private String name;
        private int age;

        public User() {
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
