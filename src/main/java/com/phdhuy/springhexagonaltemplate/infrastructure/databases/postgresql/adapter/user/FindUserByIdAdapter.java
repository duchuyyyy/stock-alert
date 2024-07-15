package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.adapter.user;

import com.phdhuy.springhexagonaltemplate.domain.model.User;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.user.FindUserByIdPort;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository.UserRepository;
import com.phdhuy.springhexagonaltemplate.infrastructure.mapper.UserMapper;
import com.phdhuy.springhexagonaltemplate.shared.annotation.PersistenceAdapter;
import com.phdhuy.springhexagonaltemplate.shared.constant.MessageConstant;
import com.phdhuy.springhexagonaltemplate.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class FindUserByIdAdapter implements FindUserByIdPort {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  @Override
  public User findByUserId(UUID userId) {
    return userMapper.toUserDomain(
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NotFoundException(MessageConstant.USER_NOT_FOUND)));
  }
}
