package com.template.template.repository.user;

import com.template.template.dto.user.RegisterUserDto;
import com.template.template.entity.UserEntity;

public interface IUserRepository {
  UserEntity register(RegisterUserDto registerUserDto);
}
