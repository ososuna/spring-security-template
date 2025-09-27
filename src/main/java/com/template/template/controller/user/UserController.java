package com.template.template.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.template.template.dto.user.RegisterUserRequestDto;
import com.template.template.dto.user.RegisterUserResponseDto;
import com.template.template.exception.BadRequestException;
import com.template.template.service.user.IUserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController implements UserApi {

  private final IUserService userService;

  @Override
  @PostMapping
  public ResponseEntity<RegisterUserResponseDto> register(@RequestBody RegisterUserRequestDto createUserRequestDto) throws BadRequestException {
    return new ResponseEntity<>(userService.register(createUserRequestDto), HttpStatus.CREATED);
  }

}