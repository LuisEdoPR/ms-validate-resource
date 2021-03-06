package com.waes.demo.component;

import static com.waes.demo.component.TestContext.ContextKey.RESOURCE;
import static com.waes.demo.component.TestContext.ContextKey.RESPONSE;
import static com.waes.demo.component.TestContext.ContextKey.TOKEN;
import static com.waes.demo.component.TestContext.getValueFromContext;
import static com.waes.demo.component.TestContext.updateContext;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.waes.demo.domain.JsonMother;
import com.waes.demo.domain.Resource;
import com.waes.demo.domain.ResourceMother;
import com.waes.demo.domain.ResourceRepository;
import com.waes.demo.infrastructure.api.dto.JsonDTO;
import com.waes.demo.infrastructure.persistence.SpringResourceRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

public class CreateResourceStepDefs extends SpringIntegrationTest {

  public static final String RIGHT = "right";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ResourceRepository resourceRepository;

  @Autowired
  private SpringResourceRepository springResourceRepository;

  @Before
  public void setUp() {
    TestContext.clearContext();
    springResourceRepository.deleteAll();
  }

  @Given("a valid resource without {word} data")
  public void validResourceWithoutDataJson(String side) {
    var resource = ResourceMother.randomWithOnlyRight();
    if (RIGHT.equals(side)) {
      resource = ResourceMother.randomWithOnlyLeft();
    }
    resourceRepository.save(resource);
    updateContext(RESOURCE, resource);
  }

  @Given("a valid resource with empty data")
  public void validResourceWithEmptyDataJson() {
    var resource = ResourceMother.random();
    resource.setLeftData(null);
    resource.setRightData(null);
    resourceRepository.save(resource);
    updateContext(RESOURCE, resource);
  }

  @When("user create {word} data resource")
  public void userCreateSideDataResource(String side) throws Exception {
    String token = getValueFromContext(TOKEN);
    Resource resource = getValueFromContext(RESOURCE);

    JsonDTO jsonDTO = new JsonDTO(JsonMother.randomStringEncoded());
    var body = new Gson().toJson(jsonDTO);

    var resultActions = mockMvc.perform(
        post("/v1/diff/{id}/{side}", resource.getId(), side)
            .header("Authorization", "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON)
            .content(body));
    updateContext(RESPONSE, resultActions);
  }


  @Then("the application should return json already error")
  public void anErrorJsonObjectsAlreadyExists() throws Exception {
    Resource resource = getValueFromContext(RESOURCE);
    ResultActions response = getValueFromContext(RESPONSE);

    response
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.*").isNotEmpty())
        .andExpect(content().json("{'code':'WAES-005'}"))
        .andExpect(content().json("{'message':'Resource already exists'}"));
    ;

    assertThat(this.resourceRepository.exists(resource.getId())).isTrue();
  }

  @Then("a valid {word} json encoded should be created")
  public void aValidJsonEncodedShouldBeCreated(String side) throws Exception {
    Resource resource = getValueFromContext(RESOURCE);
    ResultActions response = getValueFromContext(RESPONSE);

    String message = "Left Data created successfully";
    if (RIGHT.equals(side)) {
      message = "Right Data created successfully";
    }

    response
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().string(message));
    ;

    assertThat(this.resourceRepository.exists(resource.getId())).isTrue();
  }


}
