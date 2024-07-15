package com.phdhuy.springhexagonaltemplate.domain.ports.outbound.user;

import com.phdhuy.springhexagonaltemplate.domain.model.User;

import java.util.UUID;

public interface FindUserByIdPort {

  User findByUserId(UUID userId);
}
