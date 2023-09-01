package com.spring.boot.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
public class HelloController {

    /**
     * http://localhost:8091/hello
     *
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        log.info("call hello......");
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
