package com.spring.boot.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/example")
@RestController
public class ExampleController {

    /**
     * http://localhost:8091/example/hello
     *
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        log.info("call hello......");
        return "example hello";
    }

}
