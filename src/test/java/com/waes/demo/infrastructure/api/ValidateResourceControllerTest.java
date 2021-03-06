package com.waes.demo.infrastructure.api;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import com.waes.demo.application.ValidateResource;
import com.waes.demo.domain.JsonMother;
import com.waes.demo.domain.Resource;
import com.waes.demo.domain.ResourceMother;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

public class ValidateResourceControllerTest {

  @Mock
  private ValidateResource validateResource;

  private ValidateResourceController validateResourceController;

  @Before
  public void setup() {
    initMocks(this);
    validateResourceController = new ValidateResourceController(validateResource);
  }

  @Test
  public void shouldReturnJsonSuccessfully() {
    //GIVEN
    var jsonEncoded = JsonMother.randomEncoded();
    var resourceId = "1";
    given(validateResource.compare(eq(resourceId))).willReturn(jsonEncoded);
    //WHEN
    var response = validateResourceController.diffApi(resourceId);
    //THEN
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    Map<String, Object> mapResult = response.getBody();
    assertThat(mapResult.size()).isEqualTo(jsonEncoded.size());
    mapResult.keySet().forEach(key -> {
      assertThat(mapResult.get(key)).isEqualTo(jsonEncoded.get(key).getAsString());
    });
  }

  @Test
  public void shouldReturnAllResourcesSuccessfully() {
    //GIVEN
    List<Resource> resourceList = ResourceMother.randomList(5);
    given(validateResource.findAll()).willReturn(resourceList);
    //WHEN
    var response = validateResourceController.findAll();
    //THEN
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    List<Resource> responseList = response.getBody();
    assertThat(responseList.size()).isEqualTo(resourceList.size());
    assertThat(responseList.containsAll(resourceList)).isTrue();
  }

}