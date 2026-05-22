package fr.blueprynt.nodes.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Wraps the raw config map stored in a blueprint node's {@code data} field and
 * provides typed accessor helpers.
 */
public class NodeData {
    private final Map<String, Object> data;

    public NodeData(Map<String, Object> data) {
        this.data = data == null ? Map.of() : data;
    }

    public String getString(String key) {
        Object value = data.get(key);
        return value == null ? "" : String.valueOf(value);
    }

    public int getInt(String key, int defaultValue) {
        Object value = data.get(key);
        if (value instanceof Number number) {
            return number.intValue();
        }
        try {
            return value == null ? defaultValue : Integer.parseInt(String.valueOf(value));
        } catch (NumberFormatException ignored) {
            return defaultValue;
        }
    }

    public double getDouble(String key, double defaultValue) {
        Object value = data.get(key);
        if (value instanceof Number number) {
            return number.doubleValue();
        }
        try {
            return value == null ? defaultValue : Double.parseDouble(String.valueOf(value));
        } catch (NumberFormatException ignored) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        Object value = data.get(key);
        return value == null ? defaultValue : Boolean.parseBoolean(String.valueOf(value));
    }

    @SuppressWarnings("unchecked")
    public List<String> getList(String key) {
        Object value = data.get(key);
        if (value instanceof List<?> list) {
            return list.stream().map(String::valueOf).toList();
        }
        // Basic JSON array parsing for MULTIBOX fields (e.g. ["a","b","c"])
        if (value instanceof String str) {
            str = str.trim();
            if (!str.startsWith("[") || !str.endsWith("]")) return List.of();
            str = str.substring(1, str.length() - 1).trim();
            if (str.isEmpty()) return List.of();
            List<String> result = new ArrayList<>();
            for (String part : str.split(",")) {
                String s = part.trim();
                if (s.startsWith("\"") && s.endsWith("\"") && s.length() >= 2) {
                    s = s.substring(1, s.length() - 1);
                }
                if (!s.isEmpty()) result.add(s);
            }
            return Collections.unmodifiableList(result);
        }
        return List.of();
    }

    public Map<String, Object> raw() {
        return data;
    }
}
