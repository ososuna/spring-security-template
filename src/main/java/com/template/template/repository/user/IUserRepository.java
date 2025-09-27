package com.template.template.repository.user;

import com.template.template.dto.user.RegisterUserDto;
import com.template.template.entity.UserEntity;
import com.template.template.exception.NotFoundException;

public interface IUserRepository {
  UserEntity register(RegisterUserDto registerUserDto);
  UserEntity findByEmail(String email) throws NotFoundException;
  boolean existsByEmail(String email);
}
