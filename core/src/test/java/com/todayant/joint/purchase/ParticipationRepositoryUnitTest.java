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
import com.todayant.joint.purchase.persistence.Participation;
import com.todayant.joint.purchase.persistence.ParticipationRepository;
import com.todayant.joint.purchase.persistence.Purchase;
import com.todayant.joint.purchase.persistence.PurchaseRepository;
import com.todayant.joint.user.persistence.User;
import com.todayant.joint.user.persistence.UserRepository;

import static java.time.ZoneOffset.UTC;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Slf4j
@JpaRepositoryTest
public class ParticipationRepositoryUnitTest {

  @Autowired private ParticipationRepository repository;
  @Autowired private UserRepository userRepository;
  @Autowired private PurchaseRepository purchaseRepository;
  @Autowired private ProductRepository productRepository;
  @MockBean private DateTimeProvider dateTimeProvider;

  @BeforeEach
  void setUp() throws Exception {
    given(dateTimeProvider.getNow()).willReturn(Optional.of(ZonedDateTime.now(UTC)));
  }

  @Test
  void saveSuccess() {
    Participation participation = assertDoesNotThrow(this::save);
  }

  private Participation save() {
    User user = createUser();
    Purchase purchase = createPurchase();

    Participation participation =
        Participation.builder()
            .user(user)
            .groupPurchase(purchase)
            .participationDate(ZonedDateTime.now(UTC))
            .participationQuantity(100)
            .build();

    return repository.save(participation);
  }

  private Purchase createPurchase() {
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

    return purchaseRepository.save(purchase);
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
