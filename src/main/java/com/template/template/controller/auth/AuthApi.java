package com.template.template.controller.auth;

import org.springframework.http.ResponseEntity;

import com.template.template.dto.user.RegisterUserRequestDto;
import com.template.template.dto.user.RegisterUserResponseDto;
import com.template.template.exception.BadRequestException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth", description = "The auth API")
public interface AuthApi {

  @Operation(summary = "Register a new user", description = "Receive email and password, do required validations, and create a new user in the database")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Successful operation")
  })
  ResponseEntity<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto) throws BadRequestException;

}