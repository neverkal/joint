package com.todayant.joint.purchase.persistence;

import java.time.ZonedDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import com.todayant.joint.user.persistence.User;

@Entity
@FieldNameConstants
@Getter
@ToString(callSuper = true)
@Table(name = "participation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participation extends AbstractBaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment("참여 ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @Comment("참여한 사용자 ID")
  private User user;

  @ManyToOne
  @JoinColumn(name = "group_purchase_id", nullable = false)
  @Comment("연결된 공동구매 ID")
  private Purchase groupPurchase;

  @Column(name = ColumnConstant.PARTICIPATION_QUANTITY, nullable = false)
  @Comment("참여 수량")
  private Integer participationQuantity;

  @Column(name = ColumnConstant.PARTICIPATION_DATE, nullable = false)
  @Comment("참여 일자")
  private ZonedDateTime participationDate;

  @Builder
  public Participation(
      User user,
      Purchase groupPurchase,
      Integer participationQuantity,
      ZonedDateTime participationDate) {
    this.user = Objects.requireNonNull(user);
    this.groupPurchase = Objects.requireNonNull(groupPurchase);
    this.participationQuantity = Objects.requireNonNull(participationQuantity);
    this.participationDate = Objects.requireNonNull(participationDate);
  }
}
