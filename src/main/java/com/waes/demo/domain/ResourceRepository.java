package com.waes.demo.domain;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository {

  List<Resource> findAll();

  Optional<Resource> findById(String resourceId);

  void save(Resource resource);

  boolean exists(String resourceId);

}
