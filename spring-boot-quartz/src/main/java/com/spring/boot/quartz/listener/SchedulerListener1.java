package com.spring.boot.quartz.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

@Slf4j
public class SchedulerListener1 implements SchedulerListener {
    @Override
    public void jobScheduled(Trigger trigger) {
        log.info("SchedulerListener1 jobScheduled .......");
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        log.info("SchedulerListener1 jobUnscheduled .......");
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        log.info("SchedulerListener1 triggerFinalized .......");
    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        log.info("SchedulerListener1 triggerPaused .......");
    }

    @Override
    public void triggersPaused(String triggerGroup) {
        log.info("SchedulerListener1 triggersPaused .......");
    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        log.info("SchedulerListener1 triggerResumed .......");
    }

    @Override
    public void triggersResumed(String triggerGroup) {
        log.info("SchedulerListener1 triggersResumed .......");
    }

    @Override
    public void jobAdded(JobDetail jobDetail) {
        log.info("SchedulerListener1 jobAdded .......");
    }

    @Override
    public void jobDeleted(JobKey jobKey) {
        log.info("SchedulerListener1 jobDeleted .......");
    }

    @Override
    public void jobPaused(JobKey jobKey) {
        log.info("SchedulerListener1 jobPaused .......");
    }

    @Override
    public void jobsPaused(String jobGroup) {
        log.info("SchedulerListener1 jobsPaused .......");
    }

    @Override
    public void jobResumed(JobKey jobKey) {
        log.info("SchedulerListener1 jobResumed .......");
    }

    @Override
    public void jobsResumed(String jobGroup) {
        log.info("SchedulerListener1 jobsResumed .......");
    }

    @Override
    public void schedulerError(String msg, SchedulerException cause) {
        log.info("SchedulerListener1 schedulerError .......");
    }

    @Override
    public void schedulerInStandbyMode() {
        log.info("SchedulerListener1 schedulerInStandbyMode .......");
    }

    @Override
    public void schedulerStarted() {
        log.info("SchedulerListener1 schedulerStarted .......");
    }

    @Override
    public void schedulerStarting() {
        log.info("SchedulerListener1 schedulerStarting .......");
    }

    @Override
    public void schedulerShutdown() {
        log.info("SchedulerListener1 schedulerShutdown .......");
    }

    @Override
    public void schedulerShuttingdown() {
        log.info("SchedulerListener1 schedulerShuttingdown .......");
    }

    @Override
    public void schedulingDataCleared() {
        log.info("SchedulerListener1 schedulingDataCleared .......");
    }
}
