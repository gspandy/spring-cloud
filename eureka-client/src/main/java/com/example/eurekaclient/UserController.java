package com.example.eurekaclient;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/user")
public class UserController {

        private static final Logger LOG = Logger.getLogger(UserController.class.getName());


        @RequestMapping(value = "/hello111")
        public String hi(@RequestParam String name){
           return  "123";
        }

}
