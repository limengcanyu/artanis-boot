package com.spring.boot.advice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan("com.spring.boot.advice.filter")
@SpringBootApplication
public class SpringBootAdviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdviceApplication.class, args);
    }

}
