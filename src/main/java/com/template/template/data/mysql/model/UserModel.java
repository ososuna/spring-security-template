package com.template.template.data.mysql.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class UserModel extends AbstractMetadata {
  @Column(name="email")
  private String email;

  @Column(name="password")
  private String password;

  @Column(name="role")
  private String role;
}