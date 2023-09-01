package com.spring.boot.redis;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Person {
    private Long userId;
    private Integer age;
    private String address;
}
