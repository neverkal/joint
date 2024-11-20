package com.todayant.joint.common.jpa;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.todayant.joint.common.constant.ColumnConstant;
import org.hibernate.annotations.Comment;

@FieldNameConstants
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@ToString
public class AbstractBaseEntity {

  @Comment("생성 시각")
  @CreatedDate
  @Column(
      name = ColumnConstant.CREATED_AT,
      updatable = false,
      nullable = false,
      columnDefinition = "datetime(6) default current_timestamp(6)")
  private ZonedDateTime createdAt;

  @Comment("수정 시각")
  @LastModifiedDate
  @Column(
      name = ColumnConstant.MODIFIED_AT,
      nullable = false,
      columnDefinition = "datetime(6) default current_timestamp(6) on update current_timestamp(6)")
  private ZonedDateTime modifiedAt;
}
