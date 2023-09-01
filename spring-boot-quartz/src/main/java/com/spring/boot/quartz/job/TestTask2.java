package com.spring.boot.quartz.job;

import com.spring.boot.quartz.service.ExampleService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TestTask2 extends QuartzJobBean {

    @Autowired
    private ExampleService exampleService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("TestTask2 current time: " + exampleService.echo());
    }

}
