package com.pierre.hamy.micrometer.demo.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/")
    public String getResult() {
        return "Hello World";
    }
}
