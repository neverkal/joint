package com.todayant.joint.order.persistence;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import org.hibernate.annotations.Comment;

import com.todayant.joint.common.constant.ColumnConstant;
import com.todayant.joint.common.jpa.AbstractBaseEntity;
import com.todayant.joint.user.persistence.User;

@Entity
@FieldNameConstants
@Getter
@ToString(callSuper = true)
@Table(name = "order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends AbstractBaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment("주문 ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @Comment("주문한 사용자 ID")
  private User user;

  @Column(name = ColumnConstant.ORDER_DATE, nullable = false)
  @Comment("주문일자")
  private ZonedDateTime orderDate;

  @Column(name = ColumnConstant.TOTAL_AMOUNT, nullable = false)
  @Comment("총 금액")
  private BigDecimal totalAmount;

  @Enumerated(EnumType.STRING)
  @Column(name = ColumnConstant.ORDER_STATUS, nullable = false)
  @Comment("주문 상태")
  private OrderStatus status;

  enum OrderStatus {
    PAYMENT_PENDING,
    PAID,
    SHIPPING,
    COMPLETED
  }
}
