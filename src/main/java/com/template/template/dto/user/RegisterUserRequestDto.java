package com.template.template.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserRequestDto {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
}
