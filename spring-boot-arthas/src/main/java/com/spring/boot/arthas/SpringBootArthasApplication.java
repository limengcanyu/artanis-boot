package com.spring.boot.arthas;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringBootArthasApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootArthasApplication.class, args);
    }

}
