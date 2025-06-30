import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

class TestDataUtils {

  public static Map<String, Object> loadYaml(String file, String key) {
    try {
      InputStream is = TestDataUtils.class.getClassLoader().getResourceAsStream("testdata/" + file);
      Map<String, Object> yaml = new ObjectMapper(new YAMLFactory()).readValue(is, Map.class);
      return (Map<String, Object>) yaml.get(key);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Map<String, Object> loadYamlWithOverrides(String file, String key, DataTable dt) {
    Map<String, Object> base = loadYaml(file, key);
    Map<String, Object> overrides = flatten(dt.asMap(String.class, String.class));
    return merge(base, overrides);
  }

  private static Map<String, Object> flatten(Map<String, String> flat) {
    Map<String, Object> nested = new HashMap<>();
    for (String k : flat.keySet()) {
      String[] keys = k.split("\\.");
      Map<String, Object> current = nested;
      for (int i = 0; i < keys.length - 1; i++) {
        current = (Map<String, Object>) current.computeIfAbsent(keys[i], x -> new HashMap<>());
      }
      current.put(keys[keys.length - 1], flat.get(k));
    }
    return nested;
  }

  private static Map<String, Object> merge(Map<String, Object> original, Map<String, Object> overrides) {
    for (String k : overrides.keySet()) {
      if (original.get(k) instanceof Map && overrides.get(k) instanceof Map) {
        merge((Map<String, Object>) original.get(k), (Map<String, Object>) overrides.get(k));
      } else {
        original.put(k, overrides.get(k));
      }
    }
    return original;
  }
} // End of TestDataUtils
