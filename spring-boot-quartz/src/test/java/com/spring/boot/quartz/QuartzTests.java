package com.spring.boot.quartz;

import com.spring.boot.quartz.job.TestTask2;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class QuartzTests {
    public static void main(String[] args) throws SchedulerException {
        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        JobDetail job = JobBuilder.newJob(TestTask2.class).withIdentity("testTask2").storeDurably().build();

        CronTrigger trigger = TriggerBuilder.newTrigger().forJob(job)
                .withIdentity("testTask2")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/3 * * * * ?"))
                .build();

        Date ft = sched.scheduleJob(job, trigger);

        sched.start();

        try {
            // wait five minutes to show jobs
            Thread.sleep(300L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }
    }
}
