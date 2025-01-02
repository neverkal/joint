package com.todayant.joint.purchase;

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
import com.todayant.joint.purchase.persistence.Purchase;
import com.todayant.joint.purchase.persistence.PurchaseRepository;

import static java.time.ZoneOffset.UTC;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Slf4j
@JpaRepositoryTest
public class PurchaseRepositoryUnitTest {

  @Autowired private PurchaseRepository repository;
  @Autowired private ProductRepository productRepository;
  @MockBean private DateTimeProvider dateTimeProvider;

  @BeforeEach
  void setUp() throws Exception {
    given(dateTimeProvider.getNow()).willReturn(Optional.of(ZonedDateTime.now(UTC)));
  }

  @Test
  void saveSuccess() {
    Purchase purchase = assertDoesNotThrow(this::save);
  }

  private Purchase save() {
    Product product = createProduct();

    Purchase purchase =
        Purchase.builder()
            .product(product)
            .targetQuantity(10)
            .currentQuantity(100)
            .startDate(ZonedDateTime.now(UTC))
            .endDate(ZonedDateTime.now(UTC).plusDays(10))
            .status(Purchase.GroupPurchaseStatus.IN_PROGRESS)
            .discountPrice(0)
            .build();

    return repository.save(purchase);
  }

  private Product createProduct() {
    Product product =
        Product.builder()
            .name("product1")
            .description("description")
            .category(Product.Category.KIT)
            .basePrice(3000)
            .imageUrl(URI.create("https://localhost"))
            .stockQuantity(100)
            .build();

    return productRepository.save(product);
  }
}
