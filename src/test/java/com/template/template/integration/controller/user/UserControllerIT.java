package com.template.template.integration.controller.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.template.template.data.mysql.dao.UserDao;
import com.template.template.data.mysql.model.UserModel;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("User Controller Integration Tests")
public class UserControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserDao userDao;

  @Nested
  @DisplayName("Get User")
  class GetUser {
    @Test
    public void getUserWithValidRequest() throws Exception {
      UserModel userModel = UserModel.builder()
          .id(1L)
          .firstName("Pochita")
          .lastName("Test")
          .email("pochita@test.com")
          .role("USER")
          .password("123456")
          .build();
      
      when(userDao.findByEmailAndActiveTrue(any(String.class))).thenReturn(Optional.of(userModel));
      
      mockMvc.perform(get("/user")
              .with(user("pochita@test.com").roles("USER")))
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.id").value(1L))
          .andExpect(jsonPath("$.email").value("pochita@test.com"))
          .andExpect(jsonPath("$.firstName").value("Pochita"))
          .andExpect(jsonPath("$.lastName").value("Test"))
          .andExpect(jsonPath("$.role").value("USER"));
    }

    @Test
    public void getUserWithInvalidRequest() throws Exception {
      mockMvc.perform(get("/user"))
          .andExpect(status().isUnauthorized())
          .andExpect(jsonPath("$.error").value("Unauthorized"))
          .andExpect(jsonPath("$.message").value("Authentication required"));
    }
  } 
}
