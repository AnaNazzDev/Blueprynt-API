package fr.blueprynt.nodes.api;

import fr.blueprynt.model.VarType;

public record InputHandle(String handle, String type, String label, boolean optional) {
    /** Convenience constructor — accepts the {@link VarType} enum. */
    public InputHandle(String handle, VarType type, String label, boolean optional) {
        this(handle, type.name(), label, optional);
    }

    /** Convenience constructor without {@code optional} flag (defaults to {@code false}). */
    public InputHandle(String handle, VarType type, String label) {
        this(handle, type.name(), label, false);
    }
}
