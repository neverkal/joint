package com.todayant.joint.purchase.persistence;

import java.time.ZonedDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import org.hibernate.annotations.Comment;

import com.todayant.joint.common.constant.ColumnConstant;
import com.todayant.joint.common.jpa.AbstractBaseEntity;
import com.todayant.joint.product.persistence.Product;

@Entity
@FieldNameConstants
@Getter
@ToString(callSuper = true)
@Table(name = "purchase")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase extends AbstractBaseEntity {

  @Id
  @Comment(value = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @Comment(value = "연결된 상품 ID")
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Comment(value = "목표 수량")
  @Column(name = ColumnConstant.TARGET_QUANTITY, nullable = false)
  private Integer targetQuantity;

  @Comment(value = "구매 수량")
  @Column(name = ColumnConstant.CURRENT_QUANTITY, nullable = false)
  private Integer currentQuantity;

  @Comment(value = "시작일")
  @Column(name = ColumnConstant.START_DATE, nullable = false)
  private ZonedDateTime startDate;

  @Comment(value = "종료일")
  @Column(name = ColumnConstant.END_DATE, nullable = false)
  private ZonedDateTime endDate;

  @Enumerated(EnumType.STRING)
  @Comment(value = "상태 (진행 중, 완료, 취소)")
  @Column(name = ColumnConstant.PURCHASE_STATUS, nullable = false)
  private GroupPurchaseStatus status;

  @Comment(value = "할인 가격")
  @Column(name = ColumnConstant.DISCOUNT_PRICE, nullable = false)
  private Integer discountPrice;

  public enum GroupPurchaseStatus {
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
  }

  @Builder
  public Purchase(
      Product product,
      Integer targetQuantity,
      Integer currentQuantity,
      ZonedDateTime startDate,
      ZonedDateTime endDate,
      GroupPurchaseStatus status,
      Integer discountPrice) {
    this.product = Objects.requireNonNull(product);
    this.targetQuantity = Objects.requireNonNull(targetQuantity);
    this.currentQuantity = Objects.requireNonNull(currentQuantity);
    this.startDate = Objects.requireNonNull(startDate);
    this.endDate = Objects.requireNonNull(endDate);
    this.status = status;
    this.discountPrice = Objects.requireNonNull(discountPrice);
  }
}
