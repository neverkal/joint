package com.todayant.joint.user.persistence;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AbstractBaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment(value = "사용자 ID")
  private Long id;

  @Column(name = ColumnConstant.USER_NICK_NAME, nullable = false)
  @Comment(value = "사용자 별명")
  private String nickName;

  @Column(name = ColumnConstant.USER_EMAIL, nullable = false, unique = true)
  @Comment(value = "사용자 이메일")
  private String email;

  @Column(name = ColumnConstant.USER_PASSWORD, nullable = false)
  @Comment(value = "암호화된 비밀번호")
  private String password;

  @Column(name = ColumnConstant.USER_PHONE_NUMBER)
  @Comment(value = "전화번호")
  private String phoneNumber;

  @Column(name = ColumnConstant.USER_ADDRESS)
  @Comment(value = "주소")
  private String address;

  @Builder
  public User(String nickName, String email, String password, String phoneNumber, String address) {
    this.nickName = Objects.requireNonNull(nickName);
    this.email = Objects.requireNonNull(email);
    this.password = Objects.requireNonNull(password);
    this.phoneNumber = Objects.requireNonNull(phoneNumber);
    this.address = address;
  }
}
