package com.todayant.joint.product.persistence;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import org.hibernate.annotations.Comment;

import com.todayant.joint.common.constant.ColumnConstant;
import com.todayant.joint.common.jpa.AbstractBaseEntity;

@Entity
@FieldNameConstants
@Getter
@ToString(callSuper = true)
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends AbstractBaseEntity {

  @Id
  @Comment("id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Comment(value = "상품명")
  @Column(name = ColumnConstant.PRODUCT_NAME, nullable = false)
  private String name;

  @Comment(value = "상품 설명")
  @Column(name = ColumnConstant.PRODUCT_DESCRIPTION, length = 1000)
  private String description;

  @Comment(value = "상품 카테고리")
  @Column(name = ColumnConstant.CATEGORY)
  private String category;

  @Comment(value = "상품 기본 금액")
  @Column(name = ColumnConstant.BASE_PRICE, nullable = false)
  private BigDecimal basePrice;

  @Comment(value = "상품 이미지 URL")
  @Column(name = ColumnConstant.PRODUCT_IMAGE_URL)
  private String imageUrl;

  @Comment(value = "수량")
  @Column(name = ColumnConstant.PRODUCT_QUANTITY)
  private Integer stockQuantity;
}
