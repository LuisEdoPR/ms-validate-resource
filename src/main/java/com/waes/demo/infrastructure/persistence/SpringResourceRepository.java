package com.waes.demo.infrastructure.persistence;

import com.waes.demo.infrastructure.persistence.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringResourceRepository extends JpaRepository<ResourceEntity, String> {

}
