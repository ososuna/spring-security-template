package com.template.template.mapper.user;

import com.template.template.dto.user.RegisterUserDto;
import com.template.template.dto.user.RegisterUserResponseDto;
import com.template.template.entity.UserEntity;

public interface IUserMapper<T> {
  T toUserModel(RegisterUserDto dto);
  UserEntity toUserEntity(T model);
  RegisterUserResponseDto toRegisterUserDto(UserEntity entity);
}
