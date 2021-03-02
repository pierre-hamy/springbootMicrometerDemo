package com.pierre.hamy.micrometer.demo.api;


import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Timed
public class TestController {
     // cf https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/production-ready-metrics.html#production-ready-metrics-spring-mvc
    @RequestMapping("/")
    @Timed(extraTags = { "region", "us-east-1" })
    // refer to http://localhost:8080/actuator/metrics/http.server.requests?tag=uri%3A/&tag=region%3Aus-east-1
    public String getResult() {
        return "Hello World";
    }
}
