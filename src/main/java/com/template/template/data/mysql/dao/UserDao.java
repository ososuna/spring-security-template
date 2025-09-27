package com.template.template.data.mysql.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.template.template.data.mysql.model.UserModel;

public interface UserDao extends JpaRepository<UserModel, Long> {
  Optional<UserModel> findByEmailAndActiveTrue(String email);
  boolean existsByEmail(String email);
}
