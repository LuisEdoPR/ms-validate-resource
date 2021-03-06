package com.waes.demo.domain;

import com.github.javafaker.Faker;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ResourceMother {

  public static Resource random(int id) {
    return new Resource(
        String.valueOf(id),
        JsonMother.randomStringEncoded(),
        JsonMother.randomStringEncoded());
  }

  public static Resource random() {
    return random(new Faker().number().numberBetween(100, Integer.MAX_VALUE));
  }

  public static Resource randomWithOnlyLeft() {
    return new Resource(
        String.valueOf(new Faker().number().numberBetween(100, Integer.MAX_VALUE)),
        JsonMother.randomStringEncoded(),
        null);
  }

  public static Resource randomWithOnlyRight() {
    return new Resource(
        String.valueOf(new Faker().number().numberBetween(100, Integer.MAX_VALUE)),
        null,
        JsonMother.randomStringEncoded());
  }

  public static List<Resource> randomList(int size) {
    return IntStream.rangeClosed(1, size)
        .mapToObj(ResourceMother::random)
        .collect(Collectors.toList());
  }

}
