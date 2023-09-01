package com.spring.boot.quartz.config;

import com.spring.boot.quartz.job.TestTask1;
import com.spring.boot.quartz.job.TestTask2;
import com.spring.boot.quartz.listener.JobListener1;
import com.spring.boot.quartz.listener.SchedulerListener1;
import com.spring.boot.quartz.listener.TriggerListener1;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 配置定时任务
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void init() throws SchedulerException {
        // 添加监听器
        scheduler.getListenerManager().addJobListener(new JobListener1());
        scheduler.getListenerManager().addSchedulerListener(new SchedulerListener1());
        scheduler.getListenerManager().addTriggerListener(new TriggerListener1());
    }

    @Bean
    public JobDetail testQuartz1() {
        return JobBuilder.newJob(TestTask1.class).withIdentity("testTask1").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger1() {
        //3秒执行一次
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(30)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(testQuartz1())
                .withIdentity("testTask1")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public JobDetail testQuartz2() {
        return JobBuilder.newJob(TestTask2.class).withIdentity("testTask2").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger2() {
        //cron方式，每隔3秒执行一次
        return TriggerBuilder.newTrigger().forJob(testQuartz2())
                .withIdentity("testTask2")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/30 * * * * ?"))
                .build();
    }

}
