package com.template.template.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.template.template.dto.user.LoggedUserDto;
import com.template.template.exception.NotFoundException;
import com.template.template.service.user.IUserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController implements UserApi {

  private final IUserService userService;

  @Override
  @GetMapping
  public ResponseEntity<LoggedUserDto> getUser() throws NotFoundException {
    return ResponseEntity.ok(userService.getUser());
  }

}