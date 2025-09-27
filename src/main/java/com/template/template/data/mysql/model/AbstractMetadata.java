package com.template.template.data.mysql.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractMetadata extends AbstractSimpleEntity {

  @Column(name="created_by")
  private Long createdBy;

  @Column(name="updated_by")
  private Long updatedBy;

  @Column(name="created_date")
  @CreationTimestamp
  private LocalDate createdDate;

  @Column(name="updated_date")
  @UpdateTimestamp
  private LocalDate updatedDate;

  @Column(name="active", nullable=false)
  @Builder.Default
  private boolean active = true;

}