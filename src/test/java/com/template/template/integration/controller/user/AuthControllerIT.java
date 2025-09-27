package com.template.template.integration.controller.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.template.data.mysql.dao.UserDao;
import com.template.template.data.mysql.model.UserModel;
import com.template.template.dto.user.LoginUserRequestDto;
import com.template.template.dto.user.RegisterUserRequestDto;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Auth Controller Integration Tests")
public class AuthControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private UserDao userDao;

  @MockitoBean
  private PasswordEncoder passwordEncoder;

  @Nested
  @DisplayName("Register User")
  class RegisterUser {
    @Test
    public void registerUserWithValidRequest() throws Exception {
      RegisterUserRequestDto request = new RegisterUserRequestDto("Pochita", "Test", "pochita@test.com", "123456");
      UserModel userModel = UserModel.builder()
          .id(1L)
          .firstName("Pochita")
          .lastName("Test")
          .email("pochita@test.com")
          .role("USER")
          .password("123456")
          .build();
      when(userDao.save(any(UserModel.class))).thenReturn(userModel);
      mockMvc.perform(post("/auth/register")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.token").isNotEmpty())
          .andExpect(jsonPath("$.user.id").value(1L))
          .andExpect(jsonPath("$.user.email").value("pochita@test.com"))
          .andExpect(jsonPath("$.user.firstName").value("Pochita"))
          .andExpect(jsonPath("$.user.lastName").value("Test"))
          .andExpect(jsonPath("$.user.role").value("USER"));
    }

    @Test
    public void registerUserWithExistingEmail() throws Exception {
      RegisterUserRequestDto request = new RegisterUserRequestDto("Pochita", "Test", "pochita@test.com", "123456");
      when(userDao.existsByEmail(any(String.class))).thenReturn(true);
      mockMvc.perform(post("/auth/register")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.error").value("Bad Request"))
          .andExpect(jsonPath("$.message").value("Invalid email"));
    }

    @Test
    public void registerUserWithInvalidEmail() throws Exception {
      RegisterUserRequestDto request = new RegisterUserRequestDto("Pochita", "Test", "pochita", "123456");
      mockMvc.perform(post("/auth/register")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());
    }

    @Test
    public void registerUserWithInvalidPassword() throws Exception {
      RegisterUserRequestDto request = new RegisterUserRequestDto("Pochita", "Test", "pochita@test.com", "123");
      mockMvc.perform(post("/auth/register")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());
    }

    @Test
    void registerUserUnexpectedDatabaseError() throws Exception {
      RegisterUserRequestDto request = new RegisterUserRequestDto("Pochita", "Test", "pochita@test.com", "123456");

      when(userDao.save(any(UserModel.class)))
          .thenThrow(new DataAccessResourceFailureException("Unexpected database error"));

      mockMvc.perform(post("/auth/register")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isInternalServerError())
          .andExpect(jsonPath("$.error").value("Database error"))
          .andExpect(jsonPath("$.message").value("Unexpected database error"));
    }
  }

  @Nested
  @DisplayName("Login User")
  class LoginUser {
    @Test
    public void loginUserWithValidRequest() throws Exception {
      LoginUserRequestDto request = new LoginUserRequestDto("pochita@test.com", "123456");
      UserModel userModel = UserModel.builder()
          .id(1L)
          .firstName("Pochita")
          .lastName("Test")
          .email("pochita@test.com")
          .role("USER")
          .password("123456")
          .build();
      when(userDao.findByEmailAndActiveTrue(any(String.class))).thenReturn(Optional.of(userModel));
      when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(true);
      mockMvc.perform(post("/auth/login")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.token").isNotEmpty())
          .andExpect(jsonPath("$.user.id").value(1L))
          .andExpect(jsonPath("$.user.email").value("pochita@test.com"))
          .andExpect(jsonPath("$.user.firstName").value("Pochita"))
          .andExpect(jsonPath("$.user.lastName").value("Test"))
          .andExpect(jsonPath("$.user.role").value("USER"));
    }

    @Test
    public void loginUserWithNotFoundUser() throws Exception {
      LoginUserRequestDto request = new LoginUserRequestDto("pochita@test.com", "123456");
      when(userDao.findByEmailAndActiveTrue(any(String.class))).thenReturn(Optional.empty());
      mockMvc.perform(post("/auth/login")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isNotFound());
    }

    @Test
    public void loginUserWithInvalidPassword() throws Exception {
      LoginUserRequestDto request = new LoginUserRequestDto("pochita@test.com", "123456");
      UserModel userModel = UserModel.builder()
          .id(1L)
          .firstName("Pochita")
          .lastName("Test")
          .email("pochita@test.com")
          .role("USER")
          .password("123456")
          .build();
      when(userDao.findByEmailAndActiveTrue(any(String.class))).thenReturn(Optional.of(userModel));
      when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(false);
      mockMvc.perform(post("/auth/login")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());
    }
  }
}
