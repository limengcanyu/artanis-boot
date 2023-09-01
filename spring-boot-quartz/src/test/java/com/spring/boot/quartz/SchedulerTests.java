package com.spring.boot.quartz;

import com.spring.boot.quartz.job.TestTask3;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SchedulerTests {

    @Autowired
    private Scheduler scheduler;

    @Test
    void addJob() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(TestTask3.class).withIdentity("testTask3").storeDurably().build();

        CronTrigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity("testTask3")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/30 * * * * ?"))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }


    @Test
    void updateJob() throws SchedulerException {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/30 * * * * ?");

        TriggerKey triggerKey = TriggerKey.triggerKey("testTask3", "DEFAULT");
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        trigger = trigger.getTriggerBuilder().withSchedule(cronScheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    @Test
    void pauseJob() throws SchedulerException {
        // pauseJob: 暂停该job，pauseTrigger: 暂停该trigger，其它trigger还可以触发
        scheduler.pauseJob(JobKey.jobKey("testTask3", "DEFAULT"));
    }

    @Test
    void resumeJob() throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey("testTask3", "DEFAULT"));
    }

    /**
     * 因为表有主外键约束，需按照以下顺序删除数据
     */
    @Test
    void deleteJob() throws SchedulerException {
        scheduler.deleteJob(JobKey.jobKey("testTask3", "DEFAULT"));
    }

    @Test
    void queryJob() throws SchedulerException {
        // 从数据库中查询
    }

}
