package com.template.template.dto.user;

import com.template.template.exception.BadRequestException;
import com.template.template.validators.EmailValidator;

import lombok.Data;

@Data
public class RegisterUserDto {
  private String email;
  private String password;
  private String role;

  private RegisterUserDto(String email, String password, String role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public static RegisterUserDto createWithUserAndPassword(String email, String password) throws BadRequestException {
    validateEmail(email);
    validatePassword(password);
    return new RegisterUserDto(email, password, "USER");
  }

  private static void validateEmail(String email) throws BadRequestException {
    if (email == null || email.length() == 0) {
      throw new BadRequestException("Email is required");
    }
    if (!EmailValidator.isValidEmail(email)) {
      throw new BadRequestException("Invalid email");
    }
  }

  private static void validatePassword(String password) throws BadRequestException {
    if (password == null || password.length() == 0) {
      throw new BadRequestException("Password is required");
    }
    if (password.length() < 6) {
      throw new BadRequestException("Invalid password");
    }
  }

}
