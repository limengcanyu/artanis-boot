package com.spring.boot.quartz.service.impl;

import com.spring.boot.quartz.service.ExampleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExampleServiceImpl implements ExampleService {

    @Override
    public String echo() {
        return LocalDateTime.now().toString();
    }

}
