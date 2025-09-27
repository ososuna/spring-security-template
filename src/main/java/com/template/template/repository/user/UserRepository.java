package com.template.template.repository.user;

import org.springframework.stereotype.Repository;

import com.template.template.data.mysql.jpa.UserJpa;
import com.template.template.data.mysql.model.UserModel;
import com.template.template.dto.user.RegisterUserDto;
import com.template.template.entity.UserEntity;
import com.template.template.exception.NotFoundException;
import com.template.template.mapper.user.UserMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserRepository implements IUserRepository {

  private final UserJpa userJpa;

  @Override
  public UserEntity register(RegisterUserDto registerUserDto) {
    UserModel userModel = UserMapper.userMySqlModelFromRegisterUserDto(registerUserDto);
    UserModel savedUser = userJpa.save(userModel);
    return UserMapper.userEntityFromMySqlModel(savedUser);
  }

  @Override
  public UserEntity findByEmail(String email) throws NotFoundException {
    var userModel = userJpa.findByEmailAndActiveTrue(email).orElseThrow(() -> new NotFoundException("User not found"));
    return UserMapper.userEntityFromMySqlModel(userModel);
  }
}
