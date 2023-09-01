package com.spring.boot.prometheus;

import com.alibaba.fastjson.JSON;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@SpringBootApplication
public class SpringBootPrometheusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPrometheusApplication.class, args);
    }

//    @Bean
//    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
//        return (registry) -> registry.config().commonTags("application", applicationName);
//    }


    /**
     * http://localhost:8080/hello
     *
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {

        for (int i = 0; i < 10000000; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", "U00000000000000000000" + i);
            map.put("name", "zhangsan0000000000000000000000000" + i);
            map.put("age", i);
            System.out.println(map);
        }

        return "ok";
    }

    /**
     * http://localhost:8080/alert
     *
     * @return
     */
    @RequestMapping("/alert")
    public String alert(@RequestBody Map<String, Object> map) {
        log.debug("========= webhook param: {}", JSON.toJSONString(map));
        return "webhook ok";
    }
}
