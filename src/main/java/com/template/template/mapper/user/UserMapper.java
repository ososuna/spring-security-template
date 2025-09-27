package com.template.template.mapper.user;

import org.springframework.stereotype.Component;

import com.template.template.data.mysql.model.UserModel;
import com.template.template.dto.user.RegisterUserDto;
import com.template.template.dto.user.LoggedUserDto;
import com.template.template.entity.UserEntity;

@Component
public class UserMapper implements IUserMapper<UserModel> {

  @Override
  public UserModel toUserModel(RegisterUserDto dto) {
    return UserModel.builder()
        .email(dto.getEmail())
        .password(dto.getPassword())
        .role(dto.getRole())
        .build();
  }

  @Override
  public UserEntity toUserEntity(UserModel model) {
    return UserEntity.builder()
        .email(model.getEmail())
        .password(model.getPassword())
        .role(model.getRole())
        .id(model.getId())
        .build();
  }

  @Override
  public LoggedUserDto toLoggedUserDto(UserEntity entity) {
    return LoggedUserDto.builder()
        .id(entity.getId())
        .email(entity.getEmail())
        .name(entity.getName())
        .lastName(entity.getLastName())
        .role(entity.getRole())
        .build();
  }

}
