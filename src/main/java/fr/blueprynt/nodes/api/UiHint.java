package fr.blueprynt.nodes.api;

/**
 * Strongly-typed keys for {@link NodeConfigField} UI hints.
 *
 * <p>UI hints are key/value pairs attached to a config field and sent as-is to the
 * frontend inside {@code field.uiHints}.  The backend never reads them at runtime —
 * they are purely instructions for the React inspector panel.</p>
 *
 * <p>Use the enum constants with
 * {@link NodeConfigField.Builder#uiHint(UiHint, Object)} instead of raw strings so
 * that typos are caught at compile time.</p>
 */
public enum UiHint {

    DEPENDS_ON("dependsOn"),
    STRICT_SCOPES("strictScopes"),
    RUNTIME_SCOPES("runtimeScopes"),
    ALLOWED_SCOPES("allowedScopes"),
    VARIABLE_SCOPE_FIELD("variableScopeField"),
    VARIABLE_KEY_MODE("variableKeyMode"),
    VARIABLE_KEY_FIELD("variableKeyField"),
    VARIABLE_DEFAULT_FIELD("variableDefaultField"),
    VARIABLE_COLLECTION_TYPE("variableCollectionType"),
    VARIABLE_TYPE_FOR_KEY("variableTypeForKey"),
    VARIABLE_TYPE_FIELD("variableTypeField"),
    VARIABLE_TYPE_TRANSFORM("variableTypeTransform"),
    LOCKED_WHEN_DECLARED("lockedWhenDeclared"),
    VARIABLE_MAP_KEY_TYPE_FIELD("variableMapKeyTypeField"),
    VARIABLE_MAP_VALUE_TYPE_FIELD("variableMapValueTypeField"),
    VARIABLE_DEFAULT_FOR_KEY("variableDefaultForKey"),
    VISIBLE_WHEN("visibleWhen"),
    MIN("min"),
    MAX("max"),
    HELP_TEXT("helpText"),
    ADVANCED("advanced"),
    CODE_LANGUAGE("codeLanguage"),
    WARN_PATTERN("warnPattern"),
    WARN_MESSAGE("warnMessage");

    private final String key;

    UiHint(String key) {
        this.key = key;
    }

    /**
     * Returns the JSON key transmitted to the frontend inside {@code field.uiHints}.
     */
    public String key() {
        return key;
    }
}
