package fr.blueprynt.nodes.api;

import java.util.List;
import java.util.Map;

/**
 * Strongly-typed helpers for common {@link UiHint} patterns.
 *
 * <pre>
 *   NodeConfigField.builder("field", "Label", NodeConfigKind.TEXT)
 *     .uiHint(UiHint.VISIBLE_WHEN, UiHintHelpers.visibleWhenEquals("scope", "SESSION"))
 *     .build()
 * </pre>
 */
public final class UiHintHelpers {
    private UiHintHelpers() {}

    /** Show this field only when a sibling field equals a specific value. */
    public static Map<String, Object> visibleWhenEquals(String fieldKey, Object value) {
        return Map.of("field", fieldKey, "value", value);
    }

    /** Show this field only when a sibling field equals one of multiple values. */
    public static Map<String, Object> visibleWhenIn(String fieldKey, Object... values) {
        return Map.of("field", fieldKey, "values", List.of(values));
    }

    /** Show this field only when a sibling field equals one of multiple values. */
    public static Map<String, Object> visibleWhenIn(String fieldKey, List<String> values) {
        return Map.of("field", fieldKey, "values", values);
    }

    /** Set minimum and maximum value limits for a NUMBER field (soft warning, not blocking). */
    public static Map<String, Object> minMax(int min, int max) {
        return Map.of("min", min, "max", max);
    }

    /** Set minimum and maximum value limits for a NUMBER field (decimal support). */
    public static Map<String, Object> minMax(double min, double max) {
        return Map.of("min", min, "max", max);
    }

    /** Set a minimum value limit for a NUMBER field. */
    public static Map<String, Object> min(int min) {
        return Map.of("min", min);
    }

    /** Set a maximum value limit for a NUMBER field. */
    public static Map<String, Object> max(int max) {
        return Map.of("max", max);
    }

    /** Enable soft regex validation on a TEXT/TEXTAREA field with a custom failure message. */
    public static Map<String, Object> warnPattern(String pattern, String message) {
        return Map.of("warnPattern", pattern, "warnMessage", message);
    }

    /** Add a soft regex validation rule (uses generic "Unexpected format" message). */
    public static Map<String, Object> warnPattern(String pattern) {
        return Map.of("warnPattern", pattern);
    }

    /**
     * Full setup for a variable key TEXT field with scope/type/default resolution.
     *
     * @param scopeField        config field key holding the active scope (usually {@code "scope"})
     * @param typeField         config field key where the resolved type will be stored
     * @param defaultValueField config field key for the default value field
     */
    public static Map<String, Object> variableKeyField(String scopeField, String typeField, String defaultValueField) {
        java.util.LinkedHashMap<String, Object> hints = new java.util.LinkedHashMap<>();
        hints.put(UiHint.DEPENDS_ON.key(), scopeField);
        hints.put(UiHint.VARIABLE_KEY_MODE.key(), "AUTO");
        hints.put(UiHint.VARIABLE_SCOPE_FIELD.key(), scopeField);
        hints.put(UiHint.VARIABLE_KEY_FIELD.key(), "key");
        hints.put(UiHint.VARIABLE_TYPE_FIELD.key(), typeField);
        hints.put(UiHint.VARIABLE_DEFAULT_FIELD.key(), defaultValueField);
        hints.put(UiHint.STRICT_SCOPES.key(), NodeConfigFields.STRICT_VARIABLE_SCOPES);
        hints.put(UiHint.RUNTIME_SCOPES.key(), NodeConfigFields.RUNTIME_VARIABLE_SCOPES);
        return Map.copyOf(hints);
    }

    /**
     * Full setup for a variable key TEXT field for LIST variables (extracts element type).
     *
     * @param scopeField       config field key holding the active scope
     * @param elementTypeField config field key where the extracted element type will be stored
     */
    public static Map<String, Object> variableListKeyField(String scopeField, String elementTypeField) {
        java.util.LinkedHashMap<String, Object> hints = new java.util.LinkedHashMap<>();
        hints.put(UiHint.DEPENDS_ON.key(), scopeField);
        hints.put(UiHint.VARIABLE_KEY_MODE.key(), "AUTO");
        hints.put(UiHint.VARIABLE_SCOPE_FIELD.key(), scopeField);
        hints.put(UiHint.VARIABLE_KEY_FIELD.key(), "key");
        hints.put(UiHint.VARIABLE_TYPE_FIELD.key(), elementTypeField);
        hints.put(UiHint.VARIABLE_TYPE_TRANSFORM.key(), "LIST_ELEMENT");
        hints.put(UiHint.VARIABLE_COLLECTION_TYPE.key(), "LIST");
        hints.put(UiHint.STRICT_SCOPES.key(), NodeConfigFields.STRICT_VARIABLE_SCOPES);
        hints.put(UiHint.RUNTIME_SCOPES.key(), NodeConfigFields.RUNTIME_VARIABLE_SCOPES);
        return Map.copyOf(hints);
    }

    /**
     * Full setup for a variable key TEXT field for MAP variables (extracts key and value types).
     *
     * @param scopeField     config field key holding the active scope
     * @param keyTypeField   config field key where the extracted key type will be stored
     * @param valueTypeField config field key where the extracted value type will be stored
     */
    public static Map<String, Object> variableMapKeyField(String scopeField, String keyTypeField, String valueTypeField) {
        java.util.LinkedHashMap<String, Object> hints = new java.util.LinkedHashMap<>();
        hints.put(UiHint.DEPENDS_ON.key(), scopeField);
        hints.put(UiHint.VARIABLE_KEY_MODE.key(), "AUTO");
        hints.put(UiHint.VARIABLE_SCOPE_FIELD.key(), scopeField);
        hints.put(UiHint.VARIABLE_KEY_FIELD.key(), "key");
        hints.put(UiHint.VARIABLE_MAP_KEY_TYPE_FIELD.key(), keyTypeField);
        hints.put(UiHint.VARIABLE_MAP_VALUE_TYPE_FIELD.key(), valueTypeField);
        hints.put(UiHint.VARIABLE_COLLECTION_TYPE.key(), "MAP");
        hints.put(UiHint.STRICT_SCOPES.key(), NodeConfigFields.STRICT_VARIABLE_SCOPES);
        hints.put(UiHint.RUNTIME_SCOPES.key(), NodeConfigFields.RUNTIME_VARIABLE_SCOPES);
        return Map.copyOf(hints);
    }
}
