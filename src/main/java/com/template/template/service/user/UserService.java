package com.template.template.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.template.template.data.mysql.model.UserModel;
import com.template.template.dto.user.LoggedUserDto;
import com.template.template.dto.user.LoginUserRequestDto;
import com.template.template.dto.user.RegisterUserDto;
import com.template.template.dto.user.RegisterUserRequestDto;
import com.template.template.dto.user.UserTokenResponseDto;
import com.template.template.entity.UserEntity;
import com.template.template.exception.BadRequestException;
import com.template.template.exception.NotFoundException;
import com.template.template.mapper.user.IUserMapper;
import com.template.template.repository.user.IUserRepository;
import com.template.template.security.JwtUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

  private final IUserRepository userRepository;
  private final IUserMapper<UserModel> userMapper;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  @Override
  public UserTokenResponseDto register(RegisterUserRequestDto createUserRequestDto) throws BadRequestException {
    RegisterUserDto registerUserDto = RegisterUserDto.builder()
        .firstName(createUserRequestDto.getFirstName())
        .lastName(createUserRequestDto.getLastName())
        .email(createUserRequestDto.getEmail())
        .password(createUserRequestDto.getPassword())
        .build();
    if (userRepository.existsByEmail(registerUserDto.getEmail())) {
      throw new BadRequestException("Invalid email");
    }
    registerUserDto.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
    UserEntity createdUser = userRepository.register(registerUserDto);
    LoggedUserDto loggedUser = userMapper.toLoggedUserDto(createdUser);
    String token = jwtUtil.generateToken(loggedUser.getEmail());
    return UserTokenResponseDto.builder()
        .user(loggedUser)
        .token(token)
        .build();
  }

  @Override
  public UserTokenResponseDto login(LoginUserRequestDto loginUserRequestDto)
      throws BadRequestException, NotFoundException {
    UserEntity user = userRepository.findByEmail(loginUserRequestDto.getEmail());
    if (!passwordEncoder.matches(loginUserRequestDto.getPassword(), user.getPassword())) {
      throw new BadRequestException("Invalid email or password");
    }
    String token = jwtUtil.generateToken(user.getEmail());
    LoggedUserDto loggedUser = userMapper.toLoggedUserDto(user);
    return UserTokenResponseDto.builder()
        .user(loggedUser)
        .token(token)
        .build();
  }

}
