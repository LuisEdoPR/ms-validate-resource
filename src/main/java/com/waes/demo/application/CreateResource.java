package com.waes.demo.application;

import com.waes.demo.domain.Resource;
import com.waes.demo.domain.ResourceRepository;
import com.waes.demo.domain.exception.ResourceAlreadyExistsException;
import com.waes.demo.domain.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateResource {

  public static final String LEFT = "LEFT";
  public static final String RIGHT = "RIGHT";

  private final ResourceRepository resourceRepository;

  @Autowired
  public CreateResource(ResourceRepository resourceRepository) {
    this.resourceRepository = resourceRepository;
  }


  /**
   * Method that updates the Left Json of a resource
   *
   * @param resourceId ID of resource
   * @param leftData   Base64 encoded Json
   */
  public void left(String resourceId, String leftData) {
    JsonUtil.getJsonObject(leftData);
    Resource resource = resourceRepository.findById(resourceId).orElse(new Resource(resourceId));
    if (resource.hasLeft()) {
      log.debug("Resource has already left data. {}", resource);
      throw new ResourceAlreadyExistsException(resourceId, LEFT);
    }
    resource.setLeftData(leftData);
    resourceRepository.save(resource);
  }


  /**
   * Method that updates the Right Json of a resource
   *
   * @param resourceId ID of resource
   * @param rightData  Base64 encoded Json
   */
  public void right(String resourceId, String rightData) {
    JsonUtil.getJsonObject(rightData);
    Resource resource = resourceRepository.findById(resourceId).orElse(new Resource(resourceId));
    if (resource.hasRight()) {
      log.debug("Resource has already right data. {}", resource);
      throw new ResourceAlreadyExistsException(resourceId, RIGHT);
    }
    resource.setRightData(rightData);
    resourceRepository.save(resource);
  }

}
