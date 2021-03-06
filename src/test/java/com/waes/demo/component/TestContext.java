package com.waes.demo.component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class TestContext {

  private static final Map<ContextKey, Object> CONTEXT = Collections.synchronizedMap(new HashMap<>());

  public static void clearContext() {
    CONTEXT.clear();
  }

  public static <T> void updateContext(ContextKey key, T value) {
    CONTEXT.put(key, value);
  }

  public static <T> T getValueFromContext(ContextKey key) {
    return (T) CONTEXT.get(key);
  }

  public enum ContextKey {
    RESPONSE,
    TOKEN,
    RESOURCE,
  }
}
