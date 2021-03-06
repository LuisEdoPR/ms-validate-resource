package com.waes.demo.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;

import com.waes.demo.domain.JsonMother;
import com.waes.demo.domain.Resource;
import com.waes.demo.domain.ResourceMother;
import com.waes.demo.domain.ResourceRepository;
import com.waes.demo.domain.exception.JsonInvalidException;
import com.waes.demo.domain.exception.ResourceAlreadyExistsException;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

public class CreateResourceTest {

  @Mock
  private ResourceRepository resourceRepository;

  @Captor
  private ArgumentCaptor<Resource> resourceArgumentCaptor;

  private CreateResource createResource;

  @Before
  public void setup() {
    initMocks(this);
    createResource = new CreateResource(resourceRepository);
  }

  @Test(expected = JsonInvalidException.class)
  public void shouldReturnLeftJsonInvalidException() {
    //GIVEN
    var resourceId = "1";
    var json = "Invalid Left Json";
    //WHEN
    createResource.left(resourceId, json);
  }

  @Test(expected = ResourceAlreadyExistsException.class)
  public void shouldReturnResourceAlreadyExistsExceptionWhenLeftSideExists() {
    //GIVEN
    var resourceId = "1";
    var json = JsonMother.randomStringEncoded();
    Resource resource = ResourceMother.random(1);
    given(resourceRepository.findById(resourceId)).willReturn(Optional.of(resource));
    //WHEN
    createResource.left(resourceId, json);
  }

  @Test
  public void shouldSaveLeftDataSuccessfully() {
    //GIVEN
    var json = JsonMother.randomStringEncoded();
    Resource resource = ResourceMother.randomWithOnlyRight();
    var resourceId = resource.getId();
    given(resourceRepository.findById(resourceId)).willReturn(Optional.of(resource));
    //WHEN
    createResource.left(resourceId, json);
    //THEN
    then(resourceRepository).should().save(resourceArgumentCaptor.capture());
    var resourceSaved = resourceArgumentCaptor.getValue();
    assertThat(resourceSaved.getId()).isEqualTo(resourceId);
    assertThat(resourceSaved.getLeftData()).isEqualTo(json);
  }

  @Test(expected = JsonInvalidException.class)
  public void shouldReturnRightJsonInvalidException() {
    //GIVEN
    var resourceId = "1";
    var json = "Invalid Right Json";
    //WHEN
    createResource.right(resourceId, json);
  }

  @Test(expected = ResourceAlreadyExistsException.class)
  public void shouldReturnResourceAlreadyExistsExceptionWhenRightSideExists() {
    //GIVEN
    var json = JsonMother.randomStringEncoded();
    Resource resource = ResourceMother.random();
    var resourceId = resource.getId();
    given(resourceRepository.findById(resourceId)).willReturn(Optional.of(resource));
    //WHEN
    createResource.right(resourceId, json);
  }

  @Test
  public void shouldSaveRightDataSuccessfully() {
    //GIVEN
    var json = JsonMother.randomStringEncoded();
    Resource resource = ResourceMother.randomWithOnlyLeft();
    var resourceId = resource.getId();
    given(resourceRepository.findById(resourceId)).willReturn(Optional.of(resource));
    //WHEN
    createResource.right(resourceId, json);
    //THEN
    then(resourceRepository).should().save(resourceArgumentCaptor.capture());
    var resourceSaved = resourceArgumentCaptor.getValue();
    assertThat(resourceSaved.getId()).isEqualTo(resourceId);
    assertThat(resourceSaved.getRightData()).isEqualTo(json);
  }


}