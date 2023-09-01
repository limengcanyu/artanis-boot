package com.spring.boot.advice.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * <p>Description: Filter</p>
 *
 * @author rock.jiang
 * @date 2021/5/28 15:52
 */
@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "timeCostFilter")
public class TimeCostFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        log.debug("cost time: {}ms", (System.currentTimeMillis() - start));
    }

}
