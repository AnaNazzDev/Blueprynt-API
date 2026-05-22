package fr.blueprynt.nodes.api;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class NodeConfigFields {
    public static final List<String> STRICT_VARIABLE_SCOPES  = List.of("GLOBAL", "PLAYER");
    public static final List<String> RUNTIME_VARIABLE_SCOPES = List.of("SESSION", "SESSION_PLAYER", "TEMP_GLOBAL");

    private NodeConfigFields() {}

    public static NodeConfigField text(String key, String label, String placeholder, String defaultValue) {
        return NodeConfigField.builder(key, label, NodeConfigKind.TEXT).placeholder(placeholder).defaultValue(defaultValue).build();
    }

    public static NodeConfigField textarea(String key, String label, String placeholder, String defaultValue) {
        return NodeConfigField.builder(key, label, NodeConfigKind.TEXTAREA).placeholder(placeholder).defaultValue(defaultValue).build();
    }

    public static NodeConfigField number(String key, String label, String placeholder, String defaultValue) {
        return NodeConfigField.builder(key, label, NodeConfigKind.NUMBER).placeholder(placeholder).defaultValue(defaultValue).build();
    }

    public static NodeConfigField bool(String key, String label, boolean defaultValue) {
        return NodeConfigField.builder(key, label, NodeConfigKind.BOOLEAN).defaultValue(String.valueOf(defaultValue)).build();
    }

    public static NodeConfigField select(String key, String label, String defaultValue, String... options) {
        return NodeConfigField.builder(key, label, NodeConfigKind.SELECT).defaultValue(defaultValue).options(options).build();
    }

    /**
     * A SELECT field whose options are resolved fresh on every serialization call (e.g. at
     * {@code GET /api/v1/nodes} request time), so the list always reflects current server state.
     *
     * @param key             config field key stored in the node data
     * @param label           inspector label shown above the dropdown
     * @param optionsSupplier called at serialisation time — must be thread-safe and fast
     */
    public static NodeConfigField dynamicSelect(String key, String label,
                                                java.util.function.Supplier<List<String>> optionsSupplier) {
        return NodeConfigField.builder(key, label, NodeConfigKind.SELECT)
                .defaultValue("")
                .placeholder("— select —")
                .required()
                .optionsSupplier(optionsSupplier)
                .build();
    }

    public static NodeConfigField scope(String defaultValue) {
        return NodeConfigField.builder("scope", "Scope", NodeConfigKind.SCOPE_SELECT).defaultValue(defaultValue).build();
    }

    public static NodeConfigField type(String defaultValue) {
        return NodeConfigField.builder("type", "Type", NodeConfigKind.TYPE_SELECT).defaultValue(defaultValue).build();
    }

    /** Scope selector exposing all scopes (strict + runtime). */
    public static NodeConfigField variableScope(String defaultValue) {
        return NodeConfigField.builder("scope", "Scope", NodeConfigKind.SCOPE_SELECT)
                .defaultValue(defaultValue)
                .uiHint(UiHint.VARIABLE_SCOPE_FIELD, "scope")
                .uiHint(UiHint.STRICT_SCOPES,        STRICT_VARIABLE_SCOPES)
                .uiHint(UiHint.RUNTIME_SCOPES,        RUNTIME_VARIABLE_SCOPES)
                .build();
    }

    /**
     * Scope selector restricted to runtime scopes only (SESSION, SESSION_PLAYER, TEMP_GLOBAL).
     */
    public static NodeConfigField runtimeOnlyVariableScope(String defaultValue) {
        return NodeConfigField.builder("scope", "Scope", NodeConfigKind.SCOPE_SELECT)
                .defaultValue(defaultValue)
                .uiHint(UiHint.VARIABLE_SCOPE_FIELD, "scope")
                .uiHint(UiHint.STRICT_SCOPES,        List.of())
                .uiHint(UiHint.RUNTIME_SCOPES,        RUNTIME_VARIABLE_SCOPES)
                .uiHint(UiHint.ALLOWED_SCOPES,        RUNTIME_VARIABLE_SCOPES)
                .build();
    }

    /** Variable key field — auto-fills the "type" sibling when a key is selected. */
    public static NodeConfigField variableKey(String defaultValue) {
        return variableKey(defaultValue, defaultValue);
    }

    public static NodeConfigField variableKey(String defaultValue, String placeholder) {
        return variableKey(defaultValue, placeholder, "type");
    }

    /** Variable key field — auto-fills {@code variableTypeField} when a key is selected. */
    public static NodeConfigField variableKey(String defaultValue, String placeholder, String variableTypeField) {
        return NodeConfigField.builder("key", "ID", NodeConfigKind.TEXT)
                .defaultValue(defaultValue)
                .placeholder(placeholder)
                .uiHints(variableKeyHints(variableTypeField))
                .build();
    }

    /** Variable key field for List nodes. Auto-fills "elementType" with the element type. */
    public static NodeConfigField variableListKey(String defaultValue) {
        return NodeConfigField.builder("key", "ID", NodeConfigKind.TEXT)
                .defaultValue(defaultValue)
                .placeholder(defaultValue)
                .uiHints(variableKeyHints("elementType"))
                .uiHint(UiHint.VARIABLE_TYPE_TRANSFORM,    "LIST_ELEMENT")
                .uiHint(UiHint.VARIABLE_COLLECTION_TYPE,   "LIST")
                .build();
    }

    /** Variable key field for Map nodes. Auto-fills "keyType" and "valueType". */
    public static NodeConfigField variableMapKey(String defaultValue) {
        return NodeConfigField.builder("key", "ID", NodeConfigKind.TEXT)
                .defaultValue(defaultValue)
                .placeholder(defaultValue)
                .uiHints(variableKeyHints(null))
                .uiHint(UiHint.VARIABLE_MAP_KEY_TYPE_FIELD,   "keyType")
                .uiHint(UiHint.VARIABLE_MAP_VALUE_TYPE_FIELD, "valueType")
                .uiHint(UiHint.VARIABLE_COLLECTION_TYPE,      "MAP")
                .build();
    }

    /** Type field that displays and locks to the declared type of the selected variable. */
    public static NodeConfigField variableType(String defaultValue) {
        return NodeConfigField.builder("type", "Type", NodeConfigKind.TYPE_SELECT)
                .defaultValue(defaultValue)
                .uiHint(UiHint.DEPENDS_ON,            "scope")
                .uiHint(UiHint.VARIABLE_KEY_FIELD,    "key")
                .uiHint(UiHint.VARIABLE_SCOPE_FIELD,  "scope")
                .uiHint(UiHint.VARIABLE_TYPE_FOR_KEY, true)
                .uiHint(UiHint.LOCKED_WHEN_DECLARED,  true)
                .build();
    }

    /** Default value field that shows the declared default as a placeholder. */
    public static NodeConfigField variableDefault(String label, String placeholder) {
        return text("defaultValue", label, placeholder, "")
                .tokens(NodeConfigTokenSupport.TEXT_TOKENS)
                .uiHint(UiHint.DEPENDS_ON,               "scope")
                .uiHint(UiHint.VARIABLE_KEY_FIELD,        "key")
                .uiHint(UiHint.VARIABLE_SCOPE_FIELD,      "scope")
                .uiHint(UiHint.VARIABLE_DEFAULT_FOR_KEY,  true);
    }

    public static NodeConfigField inverse() {
        return bool("inverse", "Inverse", false);
    }

    public static NodeConfigField cancelEvent() {
        return bool("cancelEvent", "Cancel Event", false);
    }

    public static NodeConfigField multibox(String key, String label, String... defaultValues) {
        String defaultJson = defaultValues.length == 0 ? "[]" :
            "[" + Arrays.stream(defaultValues)
                .map(v -> "\"" + v.replace("\"", "\\\"") + "\"")
                .collect(Collectors.joining(",")) + "]";
        return NodeConfigField.builder(key, label, NodeConfigKind.MULTIBOX)
                .defaultValue(defaultJson)
                .build();
    }

    /**
     * Like {@link #multibox} but renders as an editable list with drag-and-drop reordering.
     */
    public static NodeConfigField editableMultibox(String key, String label, String... defaultValues) {
        String defaultJson = defaultValues.length == 0 ? "[]" :
            "[" + Arrays.stream(defaultValues)
                .map(v -> "\"" + v.replace("\"", "\\\"") + "\"")
                .collect(Collectors.joining(",")) + "]";
        return NodeConfigField.builder(key, label, NodeConfigKind.EDITABLE_MULTIBOX)
                .defaultValue(defaultJson)
                .build();
    }

    /**
     * Base hint map shared by all variable key fields.
     *
     * @param variableTypeField the key of the sibling TYPE_SELECT field to auto-fill,
     *                          or {@code null} if no auto-fill is needed
     */
    public static Map<String, Object> variableKeyHints(String variableTypeField) {
        java.util.LinkedHashMap<String, Object> hints = new java.util.LinkedHashMap<>();
        hints.put(UiHint.DEPENDS_ON.key(),            "scope");
        hints.put(UiHint.VARIABLE_KEY_MODE.key(),     "AUTO");
        hints.put(UiHint.VARIABLE_SCOPE_FIELD.key(),  "scope");
        hints.put(UiHint.VARIABLE_DEFAULT_FIELD.key(),"defaultValue");
        hints.put(UiHint.STRICT_SCOPES.key(),         STRICT_VARIABLE_SCOPES);
        hints.put(UiHint.RUNTIME_SCOPES.key(),        RUNTIME_VARIABLE_SCOPES);
        if (variableTypeField != null && !variableTypeField.isBlank()) {
            hints.put(UiHint.VARIABLE_TYPE_FIELD.key(), variableTypeField);
        }
        return Map.copyOf(hints);
    }
}
