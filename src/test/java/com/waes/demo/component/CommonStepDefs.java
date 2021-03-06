package com.waes.demo.component;

import static com.waes.demo.component.TestContext.ContextKey.RESOURCE;
import static com.waes.demo.component.TestContext.ContextKey.RESPONSE;
import static com.waes.demo.component.TestContext.getValueFromContext;
import static com.waes.demo.component.TestContext.updateContext;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.waes.demo.domain.Resource;
import com.waes.demo.domain.ResourceMother;
import com.waes.demo.domain.ResourceRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class CommonStepDefs extends SpringIntegrationTest {

  @Autowired
  private ResourceRepository resourceRepository;

  @Given("a valid resource with differences")
  public void validResourceWithDifferences() {
    var resource = ResourceMother.random();
    resourceRepository.save(resource);
    updateContext(RESOURCE, resource);
  }

}
