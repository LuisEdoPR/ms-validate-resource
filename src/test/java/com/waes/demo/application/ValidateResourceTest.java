package com.waes.demo.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import com.google.gson.JsonObject;
import com.waes.demo.domain.JsonMother;
import com.waes.demo.domain.Resource;
import com.waes.demo.domain.ResourceMother;
import com.waes.demo.domain.ResourceRepository;
import com.waes.demo.domain.exception.JsonDifferentContentException;
import com.waes.demo.domain.exception.JsonDifferentSizeException;
import com.waes.demo.domain.exception.JsonInvalidException;
import com.waes.demo.domain.exception.ResourceHasNoLeftDataException;
import com.waes.demo.domain.exception.ResourceHasNoRightDataException;
import com.waes.demo.domain.exception.ResourceNotFoundException;
import com.waes.demo.domain.util.JsonUtil;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ValidateResourceTest {

  @Mock
  private ResourceRepository resourceRepository;

  private ValidateResource validateResource;

  @Before
  public void setup() {
    initMocks(this);
    validateResource = new ValidateResource(resourceRepository);
  }


  @Test(expected = ResourceNotFoundException.class)
  public void shouldReturnExceptionWhenResourceNotFoundById() {
    //GIVEN
    var resourceId = "1234567";
    given(resourceRepository.findById(resourceId)).willReturn(Optional.empty());
    //WHEN
    validateResource.compare(resourceId);
  }

  @Test(expected = ResourceHasNoLeftDataException.class)
  public void shouldReturnExceptionWhenResourceHasNoLeftData() {
    //GIVEN
    Resource resource = ResourceMother.randomWithOnlyRight();
    var resourceId = resource.getId();
    given(resourceRepository.findById(resourceId)).willReturn(Optional.of(resource));
    //WHEN
    validateResource.compare(resourceId);
  }

  @Test(expected = ResourceHasNoRightDataException.class)
  public void shouldReturnExceptionWhenResourceHasNoRightData() {
    //GIVEN
    Resource resource = ResourceMother.randomWithOnlyLeft();
    var resourceId = resource.getId();
    given(resourceRepository.findById(resourceId)).willReturn(Optional.of(resource));
    //WHEN
    validateResource.compare(resourceId);
  }

  @Test(expected = JsonInvalidException.class)
  public void shouldReturnExceptionWhenJsonIsInvalid() {
    //GIVEN
    Resource resource = ResourceMother.randomWithOnlyLeft();
    var resourceId = resource.getId();
    resource.setRightData("Json Right Invalid");
    given(resourceRepository.findById(resourceId)).willReturn(Optional.of(resource));
    //WHEN
    validateResource.compare(resourceId);
  }

  @Test(expected = JsonDifferentSizeException.class)
  public void shouldReturnExceptionWhenBothJsonHaveDifferentSize() {
    //GIVEN
    Resource resource = ResourceMother.random();
    var resourceId = resource.getId();
    resource.setRightData(JsonMother.randomStringEncodedBigSize());
    given(resourceRepository.findById(resourceId)).willReturn(Optional.of(resource));
    //WHEN
    validateResource.compare(resourceId);
  }

  @Test(expected = JsonDifferentContentException.class)
  public void shouldReturnExceptionWhenBothJsonHaveDifferentContent() {
    //GIVEN
    Resource resource = ResourceMother.random();
    var resourceId = resource.getId();
    given(resourceRepository.findById(resourceId)).willReturn(Optional.of(resource));
    //WHEN
    validateResource.compare(resourceId);
  }

  @Test
  public void shouldReturnJsonWhenBothSidesAreEquals() {
    //GIVEN
    Resource resource = ResourceMother.random();
    var resourceId = resource.getId();
    resource.setRightData(resource.getLeftData());
    JsonObject jsonObjectInitial = JsonUtil.getJsonObject(resource.getLeftData());
    given(resourceRepository.findById(resourceId)).willReturn(Optional.of(resource));
    //WHEN
    JsonObject jsonObjectResult = validateResource.compare(resourceId);
    //THEN
    assertThat(jsonObjectResult).isNotNull();
    assertThat(jsonObjectResult.size()).isEqualTo(jsonObjectInitial.size());
    assertThat(jsonObjectResult.equals(jsonObjectInitial)).isTrue();
  }

  @Test
  public void shouldReturnAllResources() {
    //GIVEN
    Resource resource = ResourceMother.random();
    given(resourceRepository.findAll()).willReturn(List.of(resource));
    //WHEN
    List<Resource> resourceList = validateResource.findAll();
    //THEN
    assertThat(resourceList).isNotNull().isNotEmpty();
    assertThat(resourceList.size()).isEqualTo(1);
    assertThat(resourceList.get(0)).isNotNull();
    assertThat(resourceList.get(0).getId()).isEqualTo(resource.getId());
    assertThat(resourceList.get(0).getLeftData()).isEqualTo(resource.getLeftData());
    assertThat(resourceList.get(0).getRightData()).isEqualTo(resource.getRightData());
  }

}