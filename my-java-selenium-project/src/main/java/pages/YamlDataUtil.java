/**
 * YamlDataUtil.java
 * Utility class to load YAML data, read nested keys, list indexes, flatten values, and integrate with Cucumber.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YamlDataUtil {

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    private static final Pattern listPattern = Pattern.compile("(.+?)\\[(\\d+)]");

    /**
     * Returns a single value for a given file, node, and key path.
     */
    public static Object getKeyFromNode(String fileName, String nodeName, String keyPath) {
        try (InputStream input = YamlDataUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) throw new RuntimeException("YAML file not found: " + fileName);
            Map<String, Object> yaml = mapper.readValue(input, Map.class);
            Object node = yaml.get(nodeName);
            if (node == null) throw new RuntimeException("Node not found: " + nodeName);
            return traversePath(node, keyPath, fileName, nodeName);
        } catch (Exception e) {
            throw new RuntimeException("Error reading YAML file: " + e.getMessage(), e);
        }
    }

    /**
     * Returns a map of keyPath -> value for given list of paths.
     */
    public static Map<String, String> getKeyMapFromNode(String fileName, String nodeName, List<String> keyPaths) {
        Map<String, String> result = new LinkedHashMap<>();
        for (String path : keyPaths) {
            Object value = getKeyFromNode(fileName, nodeName, path);
            result.put(path, value != null ? value.toString() : null);
        }
        return result;
    }

    /**
     * Overloaded method to return all key paths and values under a node.
     */
    public static Map<String, String> getKeyMapFromNode(String fileName, String nodeName) {
        List<String> allPaths = extractAllKeyPaths(fileName, nodeName);
        return getKeyMapFromNode(fileName, nodeName, allPaths);
    }

    private static List<String> extractAllKeyPaths(String fileName, String nodeName) {
        try (InputStream input = YamlDataUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) throw new RuntimeException("YAML file not found: " + fileName);
            Map<String, Object> yaml = mapper.readValue(input, Map.class);
            Object node = yaml.get(nodeName);
            if (node == null) throw new RuntimeException("Node not found: " + nodeName);
            List<String> paths = new ArrayList<>();
            collectPaths("", node, paths);
            return paths;
        } catch (Exception e) {
            throw new RuntimeException("Error reading YAML: " + e.getMessage(), e);
        }
    }

    private static void collectPaths(String prefix, Object node, List<String> paths) {
        if (node instanceof Map<?, ?> map) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                String fullPath = prefix.isEmpty() ? entry.getKey().toString() : prefix + "." + entry.getKey();
                collectPaths(fullPath, entry.getValue(), paths);
            }
        } else if (node instanceof List<?> list) {
            for (int i = 0; i < list.size(); i++) {
                String indexed = prefix + "[" + i + "]";
                collectPaths(indexed, list.get(i), paths);
            }
        } else {
            paths.add(prefix);
        }
    }

    private static Object traversePath(Object node, String path, String fileName, String nodeName) {
        String[] parts = path.split("\\.");
        Object current = node;
        for (String part : parts) {
            Matcher matcher = listPattern.matcher(part);
            if (matcher.matches()) {
                String listKey = matcher.group(1);
                int index = Integer.parseInt(matcher.group(2));
                if (!(current instanceof Map<?, ?> map) || !map.containsKey(listKey)) {
                    throw new RuntimeException("List key '" + listKey + "' not found in node '" + nodeName + "'");
                }
                Object listObj = map.get(listKey);
                if (!(listObj instanceof List<?> list)) {
                    throw new RuntimeException("Key '" + listKey + "' is not a list in '" + nodeName + "'");
                }
                if (index >= list.size()) {
                    throw new RuntimeException("Index out of bounds for list '" + listKey + "'");
                }
                current = list.get(index);
            } else {
                if (!(current instanceof Map<?, ?> map) || !map.containsKey(part)) {
                    throw new RuntimeException("Key '" + part + "' not found in path '" + path + "'");
                }
                current = map.get(part);
            }
        }
        return current;
    }
}
