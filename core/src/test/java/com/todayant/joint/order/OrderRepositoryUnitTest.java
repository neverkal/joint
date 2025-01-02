package com.todayant.joint.order;

import java.time.ZonedDateTime;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.auditing.DateTimeProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.todayant.joint.JpaRepositoryTest;
import com.todayant.joint.order.persistence.Order;
import com.todayant.joint.order.persistence.OrderRepository;
import com.todayant.joint.user.persistence.User;
import com.todayant.joint.user.persistence.UserRepository;

import static java.time.ZoneOffset.UTC;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Slf4j
@JpaRepositoryTest
public class OrderRepositoryUnitTest {

  @Autowired private OrderRepository repository;
  @Autowired private UserRepository userRepository;
  @MockBean private DateTimeProvider dateTimeProvider;

  @BeforeEach
  void setUp() throws Exception {
    given(dateTimeProvider.getNow()).willReturn(Optional.of(ZonedDateTime.now(UTC)));
  }

  @Test
  void saveSuccess() {
    Order order = assertDoesNotThrow(this::save);
  }

  private Order save() {
    User user = createUser();

    Order order =
        Order.builder().user(user).orderDate(ZonedDateTime.now(UTC)).totalAmount(1000).build();

    return repository.save(order);
  }

  private User createUser() {
    User user =
        User.builder()
            .nickName("nick-name")
            .email("test@gmail.com")
            .password("1234")
            .phoneNumber("01012341234")
            .address("서울시 광진구")
            .build();

    return userRepository.save(user);
  }
}
