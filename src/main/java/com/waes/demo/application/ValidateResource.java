package com.waes.demo.application;

import com.google.gson.JsonObject;
import com.waes.demo.domain.Resource;
import com.waes.demo.domain.ResourceRepository;
import com.waes.demo.domain.exception.JsonDifferentContentException;
import com.waes.demo.domain.exception.JsonDifferentSizeException;
import com.waes.demo.domain.exception.ResourceHasNoLeftDataException;
import com.waes.demo.domain.exception.ResourceHasNoRightDataException;
import com.waes.demo.domain.exception.ResourceNotFoundException;
import com.waes.demo.domain.util.JsonUtil;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ValidateResource {

  private final ResourceRepository resourceRepository;

  @Autowired
  public ValidateResource(ResourceRepository resourceRepository) {
    this.resourceRepository = resourceRepository;
  }

  /**
   * Method that compares the Json of a resource to determine
   * if they are the same or what the differences are
   *
   * @param resourceId ID of the resource
   * @return JsonObject with the Json in case they are equals
   */
  public JsonObject compare(String resourceId) {

    Resource resource = resourceRepository.findById(resourceId)
        .orElseThrow(() -> new ResourceNotFoundException(resourceId));

    if (!resource.hasLeft()) {
      throw new ResourceHasNoLeftDataException(resourceId);
    }
    if (!resource.hasRight()) {
      throw new ResourceHasNoRightDataException(resourceId);
    }

    return validateResource(resource);
  }


  /**
   * Method that searches and returns a list with all existing resources
   *
   * @return resources List
   */
  public List<Resource> findAll() {
    return resourceRepository.findAll();
  }

  /**
   * Method that validates the format and calculates the
   * differences between the 2 Json of the resource
   *
   * @param resource Resource being validated
   * @return JsonObject with the Json in case they are equals
   */
  private JsonObject validateResource(Resource resource) {
    JsonObject jsonLeft = JsonUtil.getJsonObject(resource.getLeftData());
    JsonObject jsonRight = JsonUtil.getJsonObject(resource.getRightData());
    log.debug("resource: \n left => {} \n right => {}", jsonLeft, jsonRight);

    if (jsonLeft.size() != jsonRight.size()) {
      throw new JsonDifferentSizeException(resource.getId());
    }

    Map<String, Object> difference = JsonUtil.getDifferences(jsonLeft, jsonRight);
    if (difference != null) {
      throw new JsonDifferentContentException(resource.getId(), difference);
    }

    return jsonLeft;
  }

}
