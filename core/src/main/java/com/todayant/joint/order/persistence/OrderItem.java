package com.todayant.joint.order.persistence;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@Table(name = "order_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends AbstractBaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment("주문 항목 ID")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  @Comment("주문 ID")
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  @Comment("상품 ID")
  private Product product;

  @Column(name = ColumnConstant.ORDER_QUANTITY, nullable = false)
  @Comment("주문 수량")
  private Integer quantity;

  @Column(name = ColumnConstant.ORDER_UNIT_PRICE, nullable = false, precision = 10, scale = 2)
  @Comment("상품 단가")
  private BigDecimal unitPrice;

  @Column(name = ColumnConstant.ORDER_SUBTOTAL, nullable = false, precision = 10, scale = 2)
  @Comment("소계 금액")
  private BigDecimal subtotal;

  @PrePersist
  @PreUpdate
  private void calculateSubtotal() {
    this.subtotal = this.unitPrice.multiply(BigDecimal.valueOf(this.quantity));
  }

  @Builder
  public OrderItem(Order order, Product product, Integer quantity, BigDecimal unitPrice) {
    this.order = Objects.requireNonNull(order);
    this.product = Objects.requireNonNull(product);
    this.quantity = Objects.requireNonNull(quantity);
    this.unitPrice = Objects.requireNonNull(unitPrice);
  }
}
