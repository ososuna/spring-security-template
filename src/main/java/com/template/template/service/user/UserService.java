package com.template.template.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.template.template.dto.user.RegisterUserDto;
import com.template.template.dto.user.RegisterUserRequestDto;
import com.template.template.dto.user.RegisterUserResponseDto;
import com.template.template.entity.UserEntity;
import com.template.template.exception.BadRequestException;
import com.template.template.exception.NotFoundException;
import com.template.template.mapper.user.UserMapper;
import com.template.template.repository.user.IUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService, UserDetailsService {

  private final IUserRepository userRepository;

  @Override
  public RegisterUserResponseDto register(RegisterUserRequestDto createUserRequestDto) throws BadRequestException {
    RegisterUserDto registerUserDto = RegisterUserDto.createWithUserAndPassword(createUserRequestDto.getEmail(),
        createUserRequestDto.getPassword());
    UserEntity createdUser = this.userRepository.register(registerUserDto);
    return UserMapper.registerUserResponseDtoFromEntity(createdUser);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      return userRepository.findByEmail(username);
    } catch (NotFoundException e) {
      e.printStackTrace();
      throw new UsernameNotFoundException("User not found");
    }
  }

}
