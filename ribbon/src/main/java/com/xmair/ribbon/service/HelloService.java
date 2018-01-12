package com.xmair.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xmair.ribbon.controller.HelloController;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    private static final Logger LOG = Logger.getLogger(HelloController.class.getName());
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        LOG.log(Level.INFO, "调用service-hi应用的info服务 "+name);
        return restTemplate.getForObject("http://SERVICE-HI/info1",String.class);
    }

    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}

