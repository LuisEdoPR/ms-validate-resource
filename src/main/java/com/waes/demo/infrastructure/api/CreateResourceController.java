package com.waes.demo.infrastructure.api;

import com.waes.demo.application.CreateResource;
import com.waes.demo.infrastructure.api.dto.JsonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/diff")
@Tag(name = "CreateResourceController", description = "Create ResourceController API")
@CrossOrigin
public class CreateResourceController {

  public static final String LEFT_DATA_CREATED_SUCCESSFULLY = "Left Data created successfully";
  public static final String RIGHT_DATA_CREATED_SUCCESSFULLY = "Right Data created successfully";

  private final CreateResource createResource;

  @Autowired
  public CreateResourceController(CreateResource createResource) {
    this.createResource = createResource;
  }


  @Operation(summary = "Update Left Json",
      description = "Method that updates the Left Json of a resource",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Successful Operation",
              content = @Content(schema = @Schema(implementation = String.class))),
          @ApiResponse(
              responseCode = "404",
              description = "Not Found",
              content = @Content)
      })
  @PostMapping(value = "/{id}/left")
  public ResponseEntity<String> leftApi(
      @PathVariable String id,
      @Validated @RequestBody JsonDTO jsonDTO) {
    createResource.left(id, jsonDTO.getValue());
    return new ResponseEntity<>(LEFT_DATA_CREATED_SUCCESSFULLY, HttpStatus.CREATED);
  }


  @Operation(summary = "Update Right Json resource",
      description = "Method that updates the Right Json of a resource",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Successful Operation",
              content = @Content(schema = @Schema(implementation = String.class))),
          @ApiResponse(
              responseCode = "404",
              description = "Not Found",
              content = @Content)
      })
  @PostMapping(value = "/{id}/right")
  public ResponseEntity<String> rightApi(
      @PathVariable String id,
      @Validated @RequestBody JsonDTO jsonDTO) {
    createResource.right(id, jsonDTO.getValue());
    return new ResponseEntity<>(RIGHT_DATA_CREATED_SUCCESSFULLY, HttpStatus.CREATED);
  }

}
