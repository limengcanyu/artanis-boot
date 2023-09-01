package com.spring.boot.quartz.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

@Slf4j
public class TriggerListener1 implements TriggerListener {
    @Override
    public String getName() {
        log.info("TriggerListener1 getName .......");
        return "triggerListener1";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        log.info("TriggerListener1 triggerFired .......");
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        log.info("TriggerListener1 vetoJobExecution .......");
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        log.info("TriggerListener1 triggerMisfired .......");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        log.info("TriggerListener1 triggerComplete .......");
    }
}
