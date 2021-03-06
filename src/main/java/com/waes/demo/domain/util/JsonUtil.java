package com.waes.demo.domain.util;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.waes.demo.domain.exception.JsonInvalidException;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

  private JsonUtil() {
  }

  /**
   * Decode Json base64.
   *
   * @return String with a base64 json decode.
   */
  public static String getJsonBase64Decode(String encodedString) {
    return new String(Base64.getDecoder().decode(encodedString));
  }

  /**
   * Encode Json base64.
   *
   * @return String with a base64 json encode.
   */
  public static String getJsonBase64Encode(String decodedString) {
    return Base64.getEncoder().encodeToString(decodedString.getBytes());
  }


  /**
   * Method that transforms an encoded Json String to a JsonObject
   *
   * @param encodedString encoded Json String
   * @return JsonObject valid
   */
  public static JsonObject getJsonObject(String encodedString) {
    try {
      String jsonString = getJsonBase64Decode(encodedString);
      return new Gson().fromJson(jsonString, JsonObject.class);
    } catch (Exception e) {
      throw new JsonInvalidException(encodedString);
    }
  }


  /**
   * Method that calculates the differences between 2 JsonObjects
   * and returns null when they are equal,
   * or returns a Map with the differences found
   *
   * @param jsonLeft  JsonObject
   * @param jsonRight JsonObject
   * @return Map with differences found
   */
  public static Map<String, Object> getDifferences(JsonObject jsonLeft, JsonObject jsonRight) {
    Map<String, Object> leftMap = getMapFromJsonObject(jsonLeft);
    Map<String, Object> rightMap = getMapFromJsonObject(jsonRight);

    MapDifference<String, Object> differences = Maps.difference(leftMap, rightMap);

    if (differences.areEqual()) {
      return null;
    }
    return getMapFromMapDifference(differences);
  }


  /**
   * Method that transforms an Object from MapDifference to Object Map
   *
   * @param differences MapDifference object
   * @return Map object
   */
  private static Map<String, Object> getMapFromMapDifference(MapDifference<String, Object> differences) {
    Map<String, Object> mapDiffering = new HashMap<>();
    Map<String, MapDifference.ValueDifference<Object>> entriesDiffering = differences.entriesDiffering();
    entriesDiffering.keySet().forEach(key -> {
      Map<String, Object> item = new HashMap<>();
      item.put("left", entriesDiffering.get(key).leftValue());
      item.put("right", entriesDiffering.get(key).rightValue());
      mapDiffering.put(key, item);
    });

    return Map.of(
        "differing", mapDiffering,
        "onlyOnLeft", differences.entriesOnlyOnLeft(),
        "onlyOnRight", differences.entriesOnlyOnRight()
    );
  }

  /**
   * Method that transforms an Object from JsonObject to Object Map
   *
   * @param jsonObject object to transforms
   * @return Map with the information of original Json
   */
  public static Map<String, Object> getMapFromJsonObject(JsonObject jsonObject) {
    Gson g = new Gson();
    Type mapType = new TypeToken<Map<String, Object>>() {
    }.getType();

    return g.fromJson(jsonObject, mapType);
  }

}
