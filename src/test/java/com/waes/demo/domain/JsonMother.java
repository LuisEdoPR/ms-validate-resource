package com.waes.demo.domain;

import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import com.waes.demo.domain.util.JsonUtil;

public final class JsonMother {

  public static JsonObject randomEncoded() {
    Faker faker = new Faker();
    JsonObject json = new JsonObject();
    json.addProperty(faker.code().ean13(), faker.dragonBall().character());
    json.addProperty(faker.code().ean13(), faker.dragonBall().character());
    json.addProperty(faker.code().ean13(), faker.dragonBall().character());
    return json;
  }

  public static String randomStringEncoded() {
    Faker faker = new Faker();
    JsonObject json = new JsonObject();
    json.addProperty(faker.code().ean13(), faker.dragonBall().character());
    json.addProperty(faker.code().ean13(), faker.dragonBall().character());
    json.addProperty(faker.code().ean13(), faker.dragonBall().character());
    return JsonUtil.getJsonBase64Encode(json.toString());
  }

  public static String randomStringEncodedBigSize() {
    Faker faker = new Faker();
    JsonObject json = new JsonObject();
    json.addProperty(faker.code().ean13(), faker.dragonBall().character());
    json.addProperty(faker.code().ean13(), faker.dragonBall().character());
    json.addProperty(faker.code().ean13(), faker.dragonBall().character());
    json.addProperty(faker.code().ean13(), faker.dragonBall().character());
    json.addProperty(faker.code().ean13(), faker.dragonBall().character());
    json.addProperty(faker.code().ean13(), faker.dragonBall().character());
    json.addProperty(faker.code().ean13(), faker.dragonBall().character());
    json.addProperty(faker.code().ean13(), faker.dragonBall().character());
    json.addProperty(faker.code().ean13(), faker.dragonBall().character());
    return JsonUtil.getJsonBase64Encode(json.toString());
  }

}
