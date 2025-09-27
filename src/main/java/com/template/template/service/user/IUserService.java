package com.template.template.service.user;

import com.template.template.dto.user.RegisterUserRequestDto;
import com.template.template.dto.user.RegisterUserResponseDto;
import com.template.template.exception.BadRequestException;

public interface IUserService {
  RegisterUserResponseDto register(RegisterUserRequestDto createUserRequestDto) throws BadRequestException;
}