package com.spring.boot.quartz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.spring.boot.quartz.entity.QrtzCronTriggers;
import com.spring.boot.quartz.entity.QrtzJobDetails;
import com.spring.boot.quartz.entity.QrtzTriggers;
import com.spring.boot.quartz.service.QrtzCronTriggersService;
import com.spring.boot.quartz.service.QrtzJobDetailsService;
import com.spring.boot.quartz.service.QrtzTriggersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootQuartzApplicationTests {

    @Autowired
    private QrtzJobDetailsService qrtzJobDetailsService;

    @Autowired
    private QrtzTriggersService qrtzTriggersService;

    @Autowired
    private QrtzCronTriggersService qrtzCronTriggersService;

    /**
     * 因为表有主外键约束，需按照以下顺序新增数据
     */
    @Test
    void addJob() {
        QrtzJobDetails jobDetails = new QrtzJobDetails();
        jobDetails.setSchedName("quartzScheduler");
        jobDetails.setJobName("testTask3");
        jobDetails.setJobGroup("DEFAULT");
        jobDetails.setJobClassName("com.spring.boot.quartz.job.TestTask3");
        jobDetails.setIsDurable("1");
        jobDetails.setIsNonconcurrent("0");
        jobDetails.setIsUpdateData("0");
        jobDetails.setRequestsRecovery("0");
        qrtzJobDetailsService.save(jobDetails);

        QrtzTriggers triggers = new QrtzTriggers();
        triggers.setSchedName("quartzScheduler");
        triggers.setTriggerName("testTask3");
        triggers.setTriggerGroup("DEFAULT");
        triggers.setJobName("testTask3");
        triggers.setJobGroup("DEFAULT");
        triggers.setNextFireTime(System.currentTimeMillis() + 3000);
        triggers.setPrevFireTime(System.currentTimeMillis());
        triggers.setPriority(5);
        triggers.setTriggerState("WAITING");
        triggers.setTriggerType("CRON");
        triggers.setStartTime(System.currentTimeMillis() + 3000);
        triggers.setEndTime(0L);
        triggers.setMisfireInstr(0);
        qrtzTriggersService.save(triggers);

        QrtzCronTriggers cronTriggers = new QrtzCronTriggers();
        cronTriggers.setSchedName("quartzScheduler");
        cronTriggers.setTriggerName("testTask3");
        cronTriggers.setTriggerGroup("DEFAULT");
        cronTriggers.setCronExpression("*/3 * * * * ?");
        qrtzCronTriggersService.save(cronTriggers);
    }

    @Test
    void updateJob() {
        QrtzCronTriggers cronTriggers = new QrtzCronTriggers();
        cronTriggers.setCronExpression("*/5 * * * * ?");

        UpdateWrapper<QrtzCronTriggers> wrapper = new UpdateWrapper<>();
        wrapper.eq("TRIGGER_NAME", "testTask3");

        qrtzCronTriggersService.update(cronTriggers, wrapper);
    }

    @Test
    void pauseJob() {
        QrtzTriggers triggers = new QrtzTriggers();
        triggers.setTriggerState("PAUSE");

        UpdateWrapper<QrtzTriggers> wrapper = new UpdateWrapper<>();
        wrapper.eq("JOB_NAME", "testTask3");

        qrtzTriggersService.update(triggers, wrapper);
    }

    @Test
    void resumeJob() {
        QrtzTriggers triggers = new QrtzTriggers();
        triggers.setTriggerState("WAITING");

        UpdateWrapper<QrtzTriggers> wrapper = new UpdateWrapper<>();
        wrapper.eq("JOB_NAME", "testTask3");

        qrtzTriggersService.update(triggers, wrapper);
    }

    /**
     * 因为表有主外键约束，需按照以下顺序删除数据
     */
    @Test
    void deleteJob() {
        QueryWrapper<QrtzCronTriggers> cronTriggersQueryWrapper = new QueryWrapper<>();
        cronTriggersQueryWrapper.eq("TRIGGER_NAME", "testTask3");
        qrtzCronTriggersService.remove(cronTriggersQueryWrapper);

        QueryWrapper<QrtzTriggers> triggersQueryWrapper = new QueryWrapper<>();
        triggersQueryWrapper.eq("TRIGGER_NAME", "testTask3");
        qrtzTriggersService.remove(triggersQueryWrapper);

        QueryWrapper<QrtzJobDetails> jobDetailsQueryWrapper = new QueryWrapper<>();
        jobDetailsQueryWrapper.eq("JOB_NAME", "testTask3");
        qrtzJobDetailsService.remove(jobDetailsQueryWrapper);
    }

    @Test
    void queryJob() {
        List<QrtzCronTriggers> cronTriggersList = qrtzCronTriggersService.list();
        cronTriggersList.forEach(System.out::println);

        List<QrtzTriggers> triggersList = qrtzTriggersService.list();
        triggersList.forEach(System.out::println);

        List<QrtzJobDetails> jobDetailsList = qrtzJobDetailsService.list();
        jobDetailsList.forEach(System.out::println);
    }

}
