package fr.blueprynt.model;

/**
 * Variable scopes supported by the Blueprynt variable engine.
 *
 * <p>Only {@link #GLOBAL} and {@link #PLAYER} are persistent (stored to DB).
 * The remaining scopes are runtime-only and reset on server restart or player disconnect.</p>
 */
public enum VarScope {
    GLOBAL        (true,  false),
    PLAYER        (true,  false),
    SESSION       (false, true),
    SESSION_PLAYER(false, true),
    TEMP_GLOBAL   (false, true);

    private final boolean persistent;
    private final boolean dynamicOnly;

    VarScope(boolean persistent, boolean dynamicOnly) {
        this.persistent  = persistent;
        this.dynamicOnly = dynamicOnly;
    }

    /** Whether this scope is stored on disk / DB. */
    public boolean isPersistent()  { return persistent;  }

    /** Whether this scope is purely runtime (SESSION, SESSION_PLAYER, TEMP_GLOBAL). */
    public boolean isDynamicOnly() { return dynamicOnly; }

    /** Returns {@code true} if {@code name} matches any enum constant (case-sensitive). */
    public static boolean isValid(String name) {
        if (name == null) return false;
        for (VarScope s : values()) if (s.name().equals(name)) return true;
        return false;
    }

    /** Parses a scope name, returning {@code fallback} on null / unknown. */
    public static VarScope parse(String name, VarScope fallback) {
        if (name == null || name.isBlank()) return fallback;
        try { return valueOf(name); } catch (IllegalArgumentException ignored) { return fallback; }
    }
}
