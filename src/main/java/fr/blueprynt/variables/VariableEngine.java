package fr.blueprynt.variables;

import fr.blueprynt.model.BlueprintVariableData;
import fr.blueprynt.model.VarScope;
import fr.blueprynt.model.VarType;

import java.util.List;
import java.util.UUID;

/**
 * Stub — the real implementation is provided at runtime by the Blueprynt plugin.
 * This class exists so addon node code that reads/writes variables compiles against it.
 *
 * <p>All methods throw {@link UnsupportedOperationException} at runtime if Blueprynt
 * is not installed. In practice, node code never runs without the plugin.</p>
 */
public class VariableEngine {

    /**
     * Reads a variable value as a String.
     *
     * @param scope        variable scope
     * @param key          variable key
     * @param playerUuid   player UUID (required for PLAYER/SESSION_PLAYER scopes)
     * @param sessionId    execution session id (required for SESSION/SESSION_PLAYER)
     * @param defaultValue fallback if the variable is not set
     */
    public String get(VarScope scope, String key, UUID playerUuid, String sessionId, String defaultValue) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /**
     * Reads a variable value as a typed {@link RuntimeVariable}.
     *
     * @param scope      variable scope
     * @param key        variable key
     * @param playerUuid player UUID (required for PLAYER/SESSION_PLAYER scopes)
     * @param sessionId  execution session id (required for SESSION/SESSION_PLAYER)
     * @return the variable, or {@code null} if not set
     */
    public RuntimeVariable getRuntime(VarScope scope, String key, UUID playerUuid, String sessionId) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /**
     * Sets a variable value.
     *
     * @param scope      variable scope
     * @param key        variable key
     * @param value      value to store (will be serialized to String for persistent scopes)
     * @param typeId     type ID string, e.g. {@code "STRING"}, {@code "INT"}, {@code "LIST<INT>"}
     * @param playerUuid player UUID (required for PLAYER/SESSION_PLAYER scopes)
     * @param sessionId  execution session id (required for SESSION/SESSION_PLAYER)
     */
    public void set(VarScope scope, String key, Object value, String typeId, UUID playerUuid, String sessionId) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /** Convenience overload accepting a {@link VarType} enum instead of a type ID string. */
    public void set(VarScope scope, String key, String value, VarType type, UUID playerUuid, String sessionId) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /**
     * Deletes a variable.
     *
     * @param scope      variable scope
     * @param key        variable key
     * @param playerUuid player UUID (required for PLAYER/SESSION_PLAYER scopes)
     */
    public void delete(VarScope scope, String key, UUID playerUuid) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /**
     * Deletes a variable (overload with session ID for SESSION scopes).
     */
    public void delete(VarScope scope, String key, UUID playerUuid, String sessionId) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /**
     * Ensures GLOBAL collection variables (LIST&lt;T&gt; / MAP&lt;K,V&gt;) declared in the
     * provided list are initialised to an empty value if they do not yet exist.
     */
    public void initCollectionsIfAbsent(List<BlueprintVariableData> declarations) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /** Clears SESSION and SESSION_PLAYER variables for the given session ID. */
    public void clearSession(String sessionId) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /** Returns the variable type registry used by this engine. */
    public VariableTypeRegistry typeRegistry() {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }
}
