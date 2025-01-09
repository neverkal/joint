package com.todayant.joint.user.persistence;

import java.time.ZonedDateTime;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.todayant.joint.JpaRepositoryTest;

import static java.time.ZoneOffset.UTC;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@JpaRepositoryTest
public class UserRepositoryUnitTest {

  private static final int PAGE_SIZE = 10;

  @Autowired private UserRepository repository;
  @MockBean private DateTimeProvider dateTimeProvider;

  @BeforeEach
  void setUp() throws Exception {
    given(dateTimeProvider.getNow()).willReturn(Optional.of(ZonedDateTime.now(UTC)));
  }

  @Test
  void saveSuccess() {
    User user = assertDoesNotThrow(this::save);
  }

  private User save() {
    User user =
        User.builder()
            .nickName("nick-name")
            .email("test@gmail.com")
            .password("1234")
            .phoneNumber("01012341234")
            .address("서울시 광진구")
            .build();

    return repository.save(user);
  }

  @Test
  void getUserById() {
    User originUser = save();
    User user = repository.getUserById(originUser.getId()).orElseThrow();

    assertEquals(user, originUser);
  }

  @Test
  void findAllByPage() {
    User originUser = save();
    Page<User> users = repository.findAllByPage(PageRequest.of(0, PAGE_SIZE));

    assertEquals(users.getContent().getFirst(), originUser);
  }
}
