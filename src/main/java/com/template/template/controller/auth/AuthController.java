package com.template.template.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.template.template.dto.user.LoginUserRequestDto;
import com.template.template.dto.user.RegisterUserRequestDto;
import com.template.template.dto.user.UserTokenResponseDto;
import com.template.template.exception.BadRequestException;
import com.template.template.exception.NotFoundException;
import com.template.template.service.user.IUserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController implements AuthApi {

  private final IUserService userService;

  @Override
  @PostMapping("/register")
  public ResponseEntity<UserTokenResponseDto> register(@RequestBody RegisterUserRequestDto createUserRequestDto)
      throws BadRequestException {
    return new ResponseEntity<>(userService.register(createUserRequestDto), HttpStatus.CREATED);
  }

  @Override
  @PostMapping("/login")
  public ResponseEntity<UserTokenResponseDto> login(@RequestBody LoginUserRequestDto loginUserRequestDto)
      throws BadRequestException, NotFoundException {
    return new ResponseEntity<>(userService.login(loginUserRequestDto), HttpStatus.OK);
  }
}
