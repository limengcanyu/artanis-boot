package com.spring.boot.advice.controller;

import com.spring.boot.advice.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EchoController {

    /**
     * http://localhost:8080/echo
     *
     * @return
     */
    @RequestMapping("/echo")
    public String echo() {
        log.debug("this is undertow app echo currentTimeMillis: {}", System.currentTimeMillis());
        return "this is echo result";
    }

    /**
     * http://localhost:8080/throwException
     *
     * @return
     */
    @RequestMapping("/throwException")
    public String throwException() throws BusinessException {
        throw new BusinessException();
    }
}
