package com.template.template.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoggedUserDto {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String role;
}
