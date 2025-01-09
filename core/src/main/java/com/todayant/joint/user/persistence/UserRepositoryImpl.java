package com.todayant.joint.user.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

import com.querydsl.jpa.JPQLQuery;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

  public UserRepositoryImpl() {
    super(User.class);
  }

  @Override
  public Page<User> findAllByPage(Pageable pageable) {
    List<User> content =
        from(QUser.user)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(QUser.user.id.desc())
            .fetch();
    JPQLQuery<User> countQuery = from(QUser.user);

    return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
  }
}
