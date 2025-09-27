package com.template.template.controller.user;

import org.springframework.http.ResponseEntity;

import com.template.template.dto.user.LoggedUserDto;
import com.template.template.exception.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "The user API")
public interface UserApi {

  @Operation(summary = "Get logged user", description = "Get the logged user's data")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation")
  })
  ResponseEntity<LoggedUserDto> getUser() throws NotFoundException;

}
