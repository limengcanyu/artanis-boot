# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/#build-image)

### What is Jib?
Jib builds optimized Docker and OCI images for your Java applications without a Docker daemon - and without deep mastery of Docker best-practices. It is available as plugins for Maven and Gradle and as a Java library.

https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin

### build image

```shell
E:\apache-maven-3.8.6\bin\mvn compile jib:build

E:\apache-maven-3.8.6\bin\mvn compile jib:buildTar

```


