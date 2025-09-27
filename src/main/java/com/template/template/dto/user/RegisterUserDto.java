package com.template.template.dto.user;

import com.template.template.exception.BadRequestException;
import com.template.template.validators.EmailValidator;

import lombok.Data;


@Data
public class RegisterUserDto {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String role;

  private RegisterUserDto(String firstName, String lastName, String email, String password, String role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role = "USER";

    public Builder firstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder lastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public Builder role(String role) {
      this.role = role;
      return this;
    }

    public RegisterUserDto build() throws BadRequestException {
      validateEmail(email);
      validatePassword(password);
      return new RegisterUserDto(firstName, lastName, email, password, role);
    }
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
