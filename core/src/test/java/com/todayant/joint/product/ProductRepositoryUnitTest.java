package com.todayant.joint.product;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.auditing.DateTimeProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.todayant.joint.JpaRepositoryTest;
import com.todayant.joint.product.persistence.Product;
import com.todayant.joint.product.persistence.ProductRepository;

import static java.time.ZoneOffset.UTC;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Slf4j
@JpaRepositoryTest
public class ProductRepositoryUnitTest {

  @Autowired private ProductRepository repository;
  @MockBean private DateTimeProvider dateTimeProvider;

  @BeforeEach
  void setUp() throws Exception {
    given(dateTimeProvider.getNow()).willReturn(Optional.of(ZonedDateTime.now(UTC)));
  }

  @Test
  void saveSuccess() {
    Product product = assertDoesNotThrow(this::save);
  }

  private Product save() {
    Product product =
        Product.builder()
            .name("product1")
            .description("description")
            .category(Product.Category.KIT)
            .basePrice(3000)
            .imageUrl(URI.create("https://localhost"))
            .stockQuantity(100)
            .build();

    return repository.save(product);
  }
}
