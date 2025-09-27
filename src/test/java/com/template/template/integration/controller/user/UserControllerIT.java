package com.template.template.integration.controller.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.template.data.mysql.jpa.UserJpa;
import com.template.template.data.mysql.model.UserModel;
import com.template.template.dto.user.RegisterUserRequestDto;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private UserJpa userJpa;

  @Test
  public void registerUserWithValidRequest() throws Exception {
    RegisterUserRequestDto request = new RegisterUserRequestDto("pochita@test.com", "123456");
    UserModel userModel = UserModel.builder()
        .id(1L)
        .email("pochita@test.com")
        .role("USER")
        .password("123456")
        .build();
    when(userJpa.save(any(UserModel.class))).thenReturn(userModel);
    mockMvc.perform(post("/user")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.email").value("pochita@test.com"))
        .andExpect(jsonPath("$.role").value("USER"));
  }

  @Test
  public void registerUserWithInvalidEmail() throws Exception {
    RegisterUserRequestDto request = new RegisterUserRequestDto("pochita", "123456");
    mockMvc.perform(post("/user")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void registerUserWithInvalidPassword() throws Exception {
    RegisterUserRequestDto request = new RegisterUserRequestDto("pochita@test.com", "123");
    mockMvc.perform(post("/user")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void registerUserUnexpectedDatabaseError() throws Exception {
    RegisterUserRequestDto request = new RegisterUserRequestDto("pochita@test.com", "123456");

    when(userJpa.save(any(UserModel.class)))
        .thenThrow(new DataAccessResourceFailureException("Unexpected database error"));

    mockMvc.perform(post("/user")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isInternalServerError())
        .andExpect(jsonPath("$.error").value("Database error"))
        .andExpect(jsonPath("$.message").value("Unexpected database error"));
  }
}
