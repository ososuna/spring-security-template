package com.template.template.controller.auth;

import org.springframework.http.ResponseEntity;

import com.template.template.dto.user.LoginUserRequestDto;
import com.template.template.dto.user.RegisterUserRequestDto;
import com.template.template.dto.user.UserTokenResponseDto;
import com.template.template.exception.BadRequestException;
import com.template.template.exception.NotFoundException;

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
    ResponseEntity<UserTokenResponseDto> register(RegisterUserRequestDto registerUserRequestDto)
            throws BadRequestException;

    @Operation(summary = "Log in a registered user", description = "Receive email and password, if user is valid then authenticate it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    ResponseEntity<UserTokenResponseDto> login(LoginUserRequestDto loginUserRequestDto)
            throws BadRequestException, NotFoundException;

}