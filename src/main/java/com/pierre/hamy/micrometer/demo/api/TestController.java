package com.pierre.hamy.micrometer.demo.api;


import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Timed
public class TestController {
     // cf https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/production-ready-metrics.html#production-ready-metrics-spring-mvc
    @RequestMapping("/getResultMetricsAvailableOnActuator")
    @Timed(extraTags = { "region", "us-east-1" })
    // refer to http://localhost:8080/actuator/metrics/http.server.requests?tag=uri%3A/getResultMetricsAvailableOnActuator&tag=region%3Aus-east-1
    // !! only available when first request on "http://localhost:8080/getResultMetricsAvailableOnActuator" has been done
    public String getResultMetricsAvailableOnActuator() {
        return "Hello World";
    }
}
