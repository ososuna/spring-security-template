package com.template.template.mapper.user;

import com.template.template.dto.user.RegisterUserDto;
import com.template.template.dto.user.LoggedUserDto;
import com.template.template.entity.UserEntity;

public interface IUserMapper<T> {
  T toUserModel(RegisterUserDto dto);
  UserEntity toUserEntity(T model);
  LoggedUserDto toLoggedUserDto(UserEntity entity);
}
