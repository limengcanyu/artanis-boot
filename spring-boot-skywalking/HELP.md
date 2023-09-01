# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/#build-image)

### 启动Skywalking

cmd中执行

cd E:\Skywalking\apache-skywalking-apm-8.9.1\bin

startup.bat

### 应用启动参数

-javaagent:E:/Skywalking/apache-skywalking-java-agent-8.11.0/skywalking-agent.jar
-DSW_AGENT_NAME=spring-boot-skywalking
-DSW_AGENT_COLLECTOR_BACKEND_SERVICES=localhost:11800
