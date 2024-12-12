package com.todayant.joint;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;

import com.redis.testcontainers.RedisContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@TestConfiguration(proxyBeanMethods = false)
public class TestContainerConfiguration {
  // todo: 이미지 주소 변경 필요
  private static final String REDIS_IMAGE = "public.ecr.aws/yogiyo/redis:6.2.7";
  private static final int REDIS_PORT = 6379;

  @Bean
  @ServiceConnection("redis")
  public GenericContainer<?> redisContainer() {
    return new RedisContainer(DockerImageName.parse(REDIS_IMAGE))
        .withExposedPorts(REDIS_PORT)
        .withLogConsumer(new Slf4jLogConsumer(log))
        .waitingFor(Wait.forLogMessage(".*Ready to accept connections.*", 1));
  }
}
