package com.template.template.mapper.user;

import com.template.template.data.mysql.model.UserModel;
import com.template.template.dto.user.RegisterUserDto;
import com.template.template.dto.user.RegisterUserResponseDto;
import com.template.template.entity.UserEntity;

public class UserMapper {
  public static UserModel userMySqlModelFromRegisterUserDto(RegisterUserDto registerUserDto) {
    return UserModel.builder()
      .email(registerUserDto.getEmail())
      .password(registerUserDto.getPassword())
      .role(registerUserDto.getRole())
      .build();
  }

  public static UserEntity userEntityFromMySqlModel(UserModel userModel) {
    return UserEntity.builder()
      .email(userModel.getEmail())
      .password(userModel.getPassword())
      .role(userModel.getRole())
      .id(userModel.getId())
      .build();
  }

  public static RegisterUserResponseDto registerUserResponseDtoFromEntity(UserEntity userEntity) {
    return RegisterUserResponseDto.builder()
      .id(userEntity.getId())
      .email(userEntity.getEmail())
      .name(userEntity.getName())
      .lastName(userEntity.getLastName())
      .role(userEntity.getRole())
      .build();
  }
}
