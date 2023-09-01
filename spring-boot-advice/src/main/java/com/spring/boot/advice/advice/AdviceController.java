package com.spring.boot.advice.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * @date 2021/5/28 15:38
 */
@RestControllerAdvice
public class AdviceController {

    /**
     * 全局异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> errorHandler(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", "系统异常，请稍后重试！");
        return map;
    }
}
