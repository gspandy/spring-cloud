package com.xmair.ribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xmair.ribbon.service.HelloService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
    private static final Logger LOG = Logger.getLogger(HelloController.class.getName());
    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/miya")
    public String hi(@RequestParam String name){
        LOG.log(Level.INFO, "调用hiservice方法 "+name);
        return helloService.hiService(name);
    }


}
