package com.spring.boot.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class SpringBootExampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootExampleApplication.class, args);
    }

    /**
     * http://localhost:8081/hello
     *
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        log.debug("hello.........................");
        return "hello";
    }

}
