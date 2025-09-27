package com.template.template.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
  private Long id;
  private String name;
  private String lastName;
  private String email;
  private String role;
  private String password;
}