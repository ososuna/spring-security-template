package com.template.template.service.user;

import com.template.template.dto.user.LoggedUserDto;
import com.template.template.dto.user.LoginUserRequestDto;
import com.template.template.dto.user.RegisterUserRequestDto;
import com.template.template.dto.user.UserTokenResponseDto;
import com.template.template.exception.BadRequestException;
import com.template.template.exception.NotFoundException;

public interface IUserService {
  UserTokenResponseDto register(RegisterUserRequestDto registerUserRequestDto) throws BadRequestException;
  UserTokenResponseDto login(LoginUserRequestDto loginUserRequestDto) throws BadRequestException, NotFoundException;
  LoggedUserDto getUser() throws NotFoundException;
}