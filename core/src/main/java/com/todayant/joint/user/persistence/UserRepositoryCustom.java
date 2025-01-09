package com.todayant.joint.user.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
  Page<User> findAllByPage(Pageable pageable);
}
