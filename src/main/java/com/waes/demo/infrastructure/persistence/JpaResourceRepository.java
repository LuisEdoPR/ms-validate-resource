package com.waes.demo.infrastructure.persistence;

import com.waes.demo.domain.Resource;
import com.waes.demo.domain.ResourceRepository;
import com.waes.demo.infrastructure.persistence.entity.ResourceEntity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaResourceRepository implements ResourceRepository {

  private final SpringResourceRepository repository;

  @Autowired
  public JpaResourceRepository(SpringResourceRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Resource> findAll() {
    return repository.findAll().stream()
        .map(resourceEntity -> Resource.create(resourceEntity.getId(), resourceEntity.getLeftData(), resourceEntity.getRightData()))
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Resource> findById(String resourceId) {
    Optional<ResourceEntity> optional = repository.findById(resourceId);
    if (optional.isEmpty()) {
      return Optional.empty();
    }

    var entity = optional.get();
    return Optional.of(Resource.create(entity.getId(), entity.getLeftData(), entity.getRightData()));

  }

  @Override
  public void save(Resource resource) {
    repository.save(new ResourceEntity(resource.getId(), resource.getLeftData(), resource.getRightData()));
  }

  @Override
  public boolean exists(String resourceId) {
    return repository.existsById(resourceId);
  }

}
