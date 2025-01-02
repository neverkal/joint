package com.todayant.joint.product.persistence;

import java.net.URI;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

  @Enumerated(EnumType.STRING)
  @Comment(value = "상품 카테고리")
  @Column(name = ColumnConstant.CATEGORY, nullable = false)
  private Category category;

  @Comment(value = "상품 기본 금액")
  @Column(name = ColumnConstant.BASE_PRICE, nullable = false)
  private Integer basePrice;

  @Comment(value = "상품 이미지 URL")
  @Column(name = ColumnConstant.PRODUCT_IMAGE_URL)
  private String imageUrl;

  @Comment(value = "수량")
  @Column(name = ColumnConstant.PRODUCT_QUANTITY)
  private Integer stockQuantity;

  public enum Category {
    FRUIT,
    VEGETABLE,
    KIT,
  }

  @Builder
  public Product(
      String name,
      String description,
      Category category,
      Integer basePrice,
      URI imageUrl,
      Integer stockQuantity) {
    this.name = Objects.requireNonNull(name);
    this.description = Objects.requireNonNull(description);
    this.category = Objects.requireNonNull(category);
    this.basePrice = Objects.requireNonNull(basePrice);
    this.imageUrl = imageUrl == null ? null : imageUrl.toString();
    this.stockQuantity = Objects.requireNonNull(stockQuantity);
  }
}
