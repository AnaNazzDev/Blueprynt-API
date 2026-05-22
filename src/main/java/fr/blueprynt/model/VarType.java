package fr.blueprynt.model;

/**
 * All handle / variable types understood by the Blueprynt engine.
 *
 * <p>{@code OBJECT} and {@code STRING} are universal wildcards and are compatible
 * with any other type. {@code EXEC} is the control-flow wire type and is never
 * used for data.</p>
 */
public enum VarType {
    EXEC,
    PLAYER,
    STRING,
    INT,
    FLOAT,
    BOOL,
    LIST,
    MAP,
    OBJECT,
    LOCATION,
    ENTITY,
    JSON;

    /** Parses a type name, returning {@link #STRING} on null / unknown. */
    public static VarType parseOrString(String name) {
        if (name == null || name.isBlank()) return STRING;
        try {
            return valueOf(name);
        } catch (IllegalArgumentException ignored) {
            return STRING;
        }
    }
}
