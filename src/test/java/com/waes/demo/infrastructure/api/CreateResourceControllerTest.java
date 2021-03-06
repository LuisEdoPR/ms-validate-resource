package com.waes.demo.infrastructure.api;

import static com.waes.demo.infrastructure.api.CreateResourceController.LEFT_DATA_CREATED_SUCCESSFULLY;
import static com.waes.demo.infrastructure.api.CreateResourceController.RIGHT_DATA_CREATED_SUCCESSFULLY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

import com.waes.demo.application.CreateResource;
import com.waes.demo.domain.JsonMother;
import com.waes.demo.infrastructure.api.dto.JsonDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

public class CreateResourceControllerTest {

  @Mock
  private CreateResource createResource;

  private CreateResourceController createResourceController;

  @Before
  public void setup() {
    initMocks(this);
    createResourceController = new CreateResourceController(createResource);
  }

  @Test
  public void shouldReturnOkMessageWhenLeftSideIsCreated() {
    //GIVEN
    var jsonEncoded = JsonMother.randomStringEncoded();
    JsonDTO jsonDTO = new JsonDTO(jsonEncoded);
    var resourceId = "1";
    //WHEN
    var response = createResourceController.leftApi(resourceId, jsonDTO);
    //THEN
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody()).isEqualTo(LEFT_DATA_CREATED_SUCCESSFULLY);
  }

  @Test
  public void shouldReturnOkMessageWhenRightSideIsCreated() {
    //GIVEN
    var jsonEncoded = JsonMother.randomStringEncoded();
    JsonDTO jsonDTO = new JsonDTO(jsonEncoded);
    var resourceId = "1";
    //WHEN
    var response = createResourceController.rightApi(resourceId, jsonDTO);
    //THEN
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody()).isEqualTo(RIGHT_DATA_CREATED_SUCCESSFULLY);
  }

}