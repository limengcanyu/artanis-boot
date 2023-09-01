# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.2/maven-plugin/reference/html/#build-image)

### 启动

```sh
docker run -it -p 6650:6650  -p 8080:8080 \
  --mount source=pulsardata,target=/pulsar/data \
  --mount source=pulsarconf,target=/pulsar/conf \
  apachepulsar/pulsar:2.10.1 \
  bin/pulsar standalone

docker run -it -p 6650:6650  -p 8080:8080 apachepulsar/pulsar:2.10.1 \
  bin/pulsar standalone

```

访问地址

http://192.168.242.129:8080



--add-opens java.base/sun.net=ALL-UNNAMED
