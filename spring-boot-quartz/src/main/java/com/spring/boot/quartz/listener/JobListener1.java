package com.spring.boot.quartz.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

@Slf4j
public class JobListener1 implements JobListener {

    @Override
    public String getName() {
        log.info("JobListener1 getName .......");
        return "jobListener1";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        log.info("JobListener1 jobToBeExecuted .......");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        log.info("JobListener1 jobExecutionVetoed .......");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        log.info("JobListener1 jobWasExecuted .......");
    }

}
