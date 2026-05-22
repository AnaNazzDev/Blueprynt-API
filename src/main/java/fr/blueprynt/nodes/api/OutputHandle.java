package fr.blueprynt.nodes.api;

import fr.blueprynt.model.VarType;

public record OutputHandle(String handle, String type, String label, boolean optional) {
    /** Convenience constructor — accepts the {@link VarType} enum. */
    public OutputHandle(String handle, VarType type, String label, boolean optional) {
        this(handle, type.name(), label, optional);
    }

    /** Convenience constructor without {@code optional} flag (defaults to {@code false}). */
    public OutputHandle(String handle, VarType type, String label) {
        this(handle, type.name(), label, false);
    }

    /** Convenience constructor for string-typed handles without {@code optional} flag. */
    public OutputHandle(String handle, String type, String label) {
        this(handle, type, label, false);
    }
}
