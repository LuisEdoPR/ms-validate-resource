package com.waes.demo.infrastructure.persistence;

import static org.assertj.core.api.BDDAssertions.then;

import com.waes.demo.domain.Resource;
import com.waes.demo.domain.ResourceMother;
import com.waes.demo.domain.ResourceRepository;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaResourceRepositoryTest {

  @Autowired
  private ResourceRepository repository;

  @Autowired
  private SpringResourceRepository springResourceRepository;

  @Before
  public void setUp() {
    springResourceRepository.deleteAll();
  }

  @Test
  public void shouldReturnTrueIfResourceExists() {
    // GIVEN
    var resource = ResourceMother.random(1);
    //WHEN
    repository.save(resource);
    // THEN
    then(repository.exists(resource.getId())).isTrue();
  }

  @Test
  public void shouldReturnFalseIfRouteDoesNotExist() {
    // GIVEN
    var resourceId = "123456";
    // THEN
    then(repository.exists(resourceId)).isFalse();
  }

  @Test
  public void shouldReturnTrueIfResourceByIdIsFound() {
    // GIVEN
    var resourceInitial = ResourceMother.random();
    repository.save(resourceInitial);
    //WHEN
    Optional<Resource> optional = repository.findById(resourceInitial.getId());
    // THEN
    then(optional.isPresent()).isTrue();
    var resourceFound = optional.get();
    then(resourceFound).isNotNull();
    then(resourceFound.getId()).isEqualTo(resourceInitial.getId());
    then(resourceFound.getLeftData()).isEqualTo(resourceInitial.getLeftData());
    then(resourceFound.getRightData()).isEqualTo(resourceInitial.getRightData());
  }

  @Test
  public void shouldReturnEmptyIfResourceByIdNotExists() {
    // GIVEN
    var resourceInitial = ResourceMother.random();
    //WHEN
    Optional<Resource> optional = repository.findById(resourceInitial.getId());
    // THEN
    then(optional.isPresent()).isFalse();
  }

  @Test
  public void shouldReturnResourceListWhenGetAll() {
    // GIVEN
    var resourceInitialOne = ResourceMother.random();
    var resourceInitialTwo = ResourceMother.random();
    repository.save(resourceInitialOne);
    repository.save(resourceInitialTwo);
    //WHEN
    List<Resource> resourceList = repository.findAll();
    // THEN
    then(resourceList).isNotNull().isNotEmpty();
    then(resourceList).contains(resourceInitialOne, resourceInitialTwo);
  }

}