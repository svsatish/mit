import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class to read values from a YAML file based on top-level node and key path.
 * Supports nested keys and list access (e.g., addresses[0].city)
 */
public class YamlDataUtil {

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    private static final Pattern listPattern = Pattern.compile("(.+)\\[(\\d+)]");

    /**
     * Retrieves a single value from a YAML file using a node and key path.
     */
    public static Object getKeyFromNode(String fileName, String nodeName, String keyPath) {
        try (InputStream input = YamlDataUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("YAML file not found: " + fileName);
            }

            Map<String, Object> yaml = mapper.readValue(input, Map.class);
            Object node = yaml.get(nodeName);

            if (node == null) {
                throw new RuntimeException("Top-level node '" + nodeName + "' not found in file: " + fileName);
            }

            return traversePath(node, keyPath, fileName, nodeName);

        } catch (Exception e) {
            throw new RuntimeException("Error reading YAML file: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves multiple values by keyPath and returns them as Map<String, String>.
     *
     * @param fileName the YAML file
     * @param nodeName the top-level node
     * @param keyPaths list of key paths (e.g., address.city, addresses[1].zip)
     * @return Map of keyPath -> String value
     */
    public static Map<String, String> getKeyMapFromNode(String fileName, String nodeName, List<String> keyPaths) {
        Map<String, String> result = new LinkedHashMap<>();
        for (String path : keyPaths) {
            Object value = getKeyFromNode(fileName, nodeName, path);
            result.put(path, value != null ? value.toString() : null);
        }
        return result;
    }

    // Internal recursive method for path traversal
    private static Object traversePath(Object node, String path, String fileName, String nodeName) {
        String[] parts = path.split("\\.");
        Object current = node;

        for (String part : parts) {
            Matcher matcher = listPattern.matcher(part);
            if (matcher.matches()) {
                // List access
                String listKey = matcher.group(1);
                int index = Integer.parseInt(matcher.group(2));

                if (!(current instanceof Map<?, ?> map) || !map.containsKey(listKey)) {
                    throw new RuntimeException("Key '" + listKey + "' not found under node '" + nodeName + "' in file '" + fileName + "'");
                }

                Object listObj = map.get(listKey);
                if (!(listObj instanceof List<?> list)) {
                    throw new RuntimeException("Key '" + listKey + "' is not a list in node '" + nodeName + "'");
                }

                if (index >= list.size()) {
                    throw new RuntimeException("Index " + index + " out of bounds for list '" + listKey + "' in node '" + nodeName + "'");
                }

                current = list.get(index);
            } else {
                // Regular key access
                if (!(current instanceof Map<?, ?> map) || !map.containsKey(part)) {
                    throw new RuntimeException("Key '" + part + "' not found in path '" + path + "' under node '" + nodeName + "' in file '" + fileName + "'");
                }
                current = map.get(part);
            }
        }

        return current;
    }
}
