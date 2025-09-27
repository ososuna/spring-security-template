package com.template.template.data.mysql.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.template.template.data.mysql.model.UserModel;

public interface UserJpa extends JpaRepository<UserModel, Long> {
  Optional<UserModel> findByEmailAndActiveTrue(String email);
}
