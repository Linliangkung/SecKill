package com.jsako.seckill.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2019/3/18
 * @Author LLJ
 * @Description
 */
@RestController
@RequestMapping("/test")
public class TestController {





    @RequestMapping
    public String test(@RequestParam(value = "age",required = true)int age){
        return "hello seckill"+age;
    }
}
