package com.example.eurekaclient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {


    @RequestMapping(value = "/redis")
    public String hi(@RequestParam String name){
        return  "123";
    }
}
