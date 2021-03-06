package com.waes.demo.infrastructure.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resource")
public class ResourceEntity {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "left_data")
  private String leftData;

  @Column(name = "right_data")
  private String rightData;

}
