package com.todayant.joint.common.config;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

import jakarta.persistence.EntityManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
@EnableEnversRepositories(basePackages = "com.todayant.joint")
@EnableTransactionManagement
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
public class SpringDataJpaConfig {

  @Bean
  public DateTimeProvider dateTimeProvider() {
    return () -> Optional.of(ZonedDateTime.now(ZoneOffset.UTC));
  }

  @Bean
  public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
    return new JPAQueryFactory(entityManager);
  }
}
