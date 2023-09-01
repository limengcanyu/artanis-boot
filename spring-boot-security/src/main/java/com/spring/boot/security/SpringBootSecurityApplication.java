package com.spring.boot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableWebSecurity
@SpringBootApplication
public class SpringBootSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityApplication.class, args);
    }

    /**
     * http://localhost:8081/hello
     * @return
     */
    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }

    /**
     * http://localhost:8081/lucy?username=rock&password=123
     * @return
     */
    @RequestMapping("lucy")
    public String lucy() {
        return "lucy";
    }

}
