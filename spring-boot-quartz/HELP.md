# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.2/maven-plugin/reference/html/#build-image)

### 多实例任务执行

多实例只有一个实例执行定时任务

### 11张表功能说明

|表明                      |功能                                                                                                                      |
|---                       |---                                                                                                                       |
|QRTZ_CALENDARS            |以 Blob 类型存储 Quartz 的 Calendar 信息                                                                                  |
|QRTZ_CRON_TRIGGERS        |存储 Cron Trigger，包括 Cron 表达式和时区信息                                                                             |
|QRTZ_FIRED_TRIGGERS       |存储与已触发的 Trigger 相关的状态信息，以及相联 Job 的执行信息                                                            |
|QRTZ_PAUSED_TRIGGER_GRPS  |存储已暂停的 Trigger 组的信息                                                                                             |
|QRTZ_SCHEDULER_STATE      |存储少量的有关 Scheduler 的状态信息，和别的 Scheduler 实例(假如是用于一个集群中)                                          |
|QRTZ_LOCKS                |存储程序的非观锁的信息(假如使用了悲观锁)                                                                                  |
|QRTZ_JOB_DETAILS          |存储每一个已配置的 Job 的详细信息                                                                                         |
|QRTZ_SIMPLE_TRIGGERS      |存储简单的 Trigger，包括重复次数，间隔，以及已触的次数                                                                    |
|QRTZ_BLOG_TRIGGERS        |Trigger 作为 Blob 类型存储(用于 Quartz 用户用 JDBC 创建他们自己定制的 Trigger 类型，JobStore 并不知道如何存储实例的时候)  |
|QRTZ_TRIGGER_LISTENERS    |存储已配置的 TriggerListener 的信息                                                                                       |
|QRTZ_TRIGGERS             |存储已配置的 Trigger 的信息                                                                                               |

### The Examples

|Title                                          |Description                                                                                                                          |
|---                                            |---                                                                                                                                  |
|Example 1 - First Quartz Program               |Think of this as a "Hello World" for Quartz                                                                                          |
|Example 2 - Simple Triggers                    |Shows a dozen different ways of using Simple Triggers to schedule your jobs                                                          |
|Example 3 - Cron Triggers                      |Shows how Cron Triggers can be used to schedule your job                                                                             |
|Example 4 - Job State and Parameters           |Demonstrates how parameters can be passed into jobs and how jobs maintain state                                                      |
|Example 5 - Handling Job Misfires              |Sometimes job will not execute when they are supposed to. See how to handle these Misfires                                           |
|Example 6 - Dealing with Job Exceptions        |No job is perfect. See how you can let the scheduler know how to deal with exceptions that are thrown by your job                    |
|Example 7 - Interrupting Jobs                  |Shows how the scheduler can interrupt your jobs and how to code your jobs to deal with interruptions                                 |
|Example 8 - Fun with Calendars                 |Demonstrates how a Holiday calendar can be used to exclude execution of jobs on a holiday                                            |
|Example 9 - Job Listeners                      |Use job listeners to have one job trigger another job, building a simple workflow                                                    |
|Example 10 - Using Quartz Plug-Ins             |Demonstrates the use of the XML Job Initialization plug-in as well as the History Logging plug-ins                                   |
|Example 11 - Quartz Under High Load            |Quartz can run a lot of jobs but see how thread pools can limit how many jobs can execute simultaneously                             |
|Example 12 - Remote Job Scheduling using RMI   |Using Remote Method Invocation, a Quartz scheduler can be remotely scheduled by a client                                             |
|Example 13 - Clustered Quartz                  |Demonstrates how Quartz can be used in a clustered environment and how Quartz can use the database to persist scheduling information |
|Example 14 - Trigger Priorities                |Demonstrates how Trigger priorities can be used to manage firing order for Triggers with the same fire time                          |
|Example 15 - TC Clustered Quartz               |Demonstrates how Quartz can be clustered with Terracotta, rather than with a database                                                |

注：
springboot使用数据库存储时，默认即是quartz集群模式，启动多个应用实例，定时任务只有一个实例执行
