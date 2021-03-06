package com.waes.demo.component;

import static com.waes.demo.component.TestContext.ContextKey.RESOURCE;
import static com.waes.demo.component.TestContext.ContextKey.RESPONSE;
import static com.waes.demo.component.TestContext.ContextKey.TOKEN;
import static com.waes.demo.component.TestContext.getValueFromContext;
import static com.waes.demo.component.TestContext.updateContext;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.waes.demo.domain.Resource;
import com.waes.demo.domain.ResourceMother;
import com.waes.demo.domain.ResourceRepository;
import com.waes.demo.infrastructure.persistence.SpringResourceRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

public class ValidateResourceStepDefs extends SpringIntegrationTest {

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

  @Given("a valid resource with equals json")
  public void validResourceWithEqualsJson() {
    var resource = ResourceMother.random();
    resource.setRightData(resource.getLeftData());
    resourceRepository.save(resource);
    updateContext(RESOURCE, resource);
  }


  @Given("a resource that not exists")
  public void aResourceThatNotExists() {
    var resource = ResourceMother.random();
    updateContext(RESOURCE, resource);
  }

  @When("all resources are listed")
  public void allResourcesAreListed() throws Exception {
    String token = getValueFromContext(TOKEN);

    var resultActions = mockMvc.perform(get("/v1/diff")
        .header("Authorization", "Bearer " + token)
        .contentType(MediaType.APPLICATION_JSON));
    updateContext(RESPONSE, resultActions);
  }

  @When("user validate resource")
  public void userValidateResource() throws Exception {
    String token = getValueFromContext(TOKEN);
    Resource resource = getValueFromContext(RESOURCE);

    var resultActions = mockMvc.perform(get("/v1/diff/{resourceId}", resource.getId())
        .header("Authorization", "Bearer " + token)
        .contentType(MediaType.APPLICATION_JSON));
    updateContext(RESPONSE, resultActions);
  }

  @Then("all the resources should be returned")
  public void allTheResourcesShouldBeReturned() throws Exception {
    Resource resource = getValueFromContext(RESOURCE);
    ResultActions response = getValueFromContext(RESPONSE);

    response
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.*").isArray())
        .andExpect(jsonPath("$.*", hasSize(greaterThan(0))));

    assertThat(this.resourceRepository.exists(resource.getId())).isTrue();
  }

  @Then("the application should return json different error")
  public void anErrorJsonObjectsAreDifferent() throws Exception {
    Resource resource = getValueFromContext(RESOURCE);
    ResultActions response = getValueFromContext(RESPONSE);

    response
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.*").isNotEmpty())
        .andExpect(content().json("{'code':'WAES-008'}"))
        .andExpect(content().json("{'message':'Json objects are different'}"))
        .andExpect(jsonPath("$.differences").isNotEmpty())
    ;

    assertThat(this.resourceRepository.exists(resource.getId())).isTrue();
  }

  @Then("a valid json encoded should be return")
  public void aValidJsonEncodedShouldBeReturn() throws Exception {
    Resource resource = getValueFromContext(RESOURCE);
    ResultActions response = getValueFromContext(RESPONSE);

    response
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.*").isArray())
        .andExpect(jsonPath("$.*", hasSize(greaterThan(0))))
    ;

    assertThat(this.resourceRepository.exists(resource.getId())).isTrue();
  }

}
