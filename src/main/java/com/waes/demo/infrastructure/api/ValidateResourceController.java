package com.waes.demo.infrastructure.api;

import com.waes.demo.application.ValidateResource;
import com.waes.demo.domain.Resource;
import com.waes.demo.domain.util.JsonUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/diff")
@Tag(name = "ValidateResourceController", description = "Validate Resource Controller API")
@CrossOrigin
public class ValidateResourceController {

  private final ValidateResource validateResource;

  @Autowired
  public ValidateResourceController(ValidateResource validateResource) {
    this.validateResource = validateResource;
  }


  @Operation(summary = "Validation by resourceId",
      description = "This Method is responsible for comparing the json of a resource " +
          "indicated by the ID sent in the url as a parameter",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Successful Operation",
              content = @Content(schema = @Schema(implementation = Map.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Bad Request",
              content = @Content),
          @ApiResponse(
              responseCode = "500",
              description = "Internal Server Error",
              content = @Content)
      })
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> diffApi(@PathVariable String id) {
    Map<String, Object> mapDiffs = JsonUtil.getMapFromJsonObject(validateResource.compare(id));
    return new ResponseEntity<>(mapDiffs, HttpStatus.OK);
  }


  @Operation(summary = "Get all resources",
      description = "Method that consults and returns all existing resources",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Successful Operation",
              content = @Content(array = @ArraySchema(schema = @Schema(implementation = Resource.class)))),
          @ApiResponse(
              responseCode = "500",
              description = "Internal Server Error",
              content = @Content)
      })
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Resource>> findAll() {
    return new ResponseEntity<>(validateResource.findAll(), HttpStatus.OK);
  }
}
