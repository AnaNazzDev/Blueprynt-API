package fr.blueprynt.engine;

import fr.blueprynt.model.BlueprintVariableData;
import fr.blueprynt.model.VarScope;
import fr.blueprynt.nodes.api.NodeData;
import fr.blueprynt.variables.VariableEngine;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ExecutionContext {
    private final Map<String, Object> runtimeValues = new HashMap<>();
    private final Map<String, Object> localVars = new HashMap<>();
    private final Player triggerPlayer;
    private final String blueprintId;
    private VariableEngine variableEngine;
    private String sessionId;
    private List<BlueprintVariableData> variableDeclarations = List.of();
    private int callDepth;
    private boolean breakRequested;

    public ExecutionContext(String blueprintId, Player triggerPlayer) {
        this(blueprintId, triggerPlayer, null, UUID.randomUUID().toString());
    }

    public ExecutionContext(String blueprintId, Player triggerPlayer, VariableEngine variableEngine, String sessionId) {
        this.blueprintId = blueprintId;
        this.triggerPlayer = triggerPlayer;
        this.variableEngine = variableEngine;
        this.sessionId = sessionId == null || sessionId.isBlank() ? UUID.randomUUID().toString() : sessionId;
        if (triggerPlayer != null) {
            set("player", triggerPlayer);
            set("player.name", triggerPlayer.getName());
        }
    }

    public <T> T resolve(String handle, NodeData config, Class<T> type) {
        Object value = runtimeValues.get(handle);
        if (value == null) {
            value = config.raw().get(handle);
        }
        if (value == null) {
            return null;
        }
        if (type.isInstance(value)) {
            return type.cast(value);
        }
        if (type == String.class) {
            return type.cast(String.valueOf(value));
        }
        return null;
    }

    public void set(String handle, Object value) {
        runtimeValues.put(handle, value);
    }

    public Object get(String handle) {
        return runtimeValues.get(handle);
    }

    /**
     * Removes a runtime value from the context.
     */
    public void clear(String handle) {
        runtimeValues.remove(handle);
    }

    public void setLocal(String key, Object value) {
        localVars.put(key, value);
    }

    public Object getLocal(String key) {
        return localVars.get(key);
    }

    public Player triggerPlayer() {
        return triggerPlayer;
    }

    public String blueprintId() {
        return blueprintId;
    }

    public void attachVariables(VariableEngine variableEngine, String sessionId) {
        this.variableEngine = variableEngine;
        this.sessionId = sessionId == null || sessionId.isBlank() ? UUID.randomUUID().toString() : sessionId;
    }

    public void attachVariableDeclarations(List<BlueprintVariableData> declarations) {
        this.variableDeclarations = declarations == null ? List.of() : List.copyOf(declarations);
    }

    public List<BlueprintVariableData> variableDeclarations() {
        return variableDeclarations;
    }

    public Optional<BlueprintVariableData> variableDeclaration(VarScope scope, String key) {
        if (scope == null || key == null || key.isBlank()) {
            return Optional.empty();
        }
        return variableDeclarations.stream()
                .filter(variable -> scope == variable.scope())
                .filter(variable -> key.equals(variable.key()))
                .findFirst();
    }

    public VariableEngine variableEngine() {
        return variableEngine;
    }

    public String sessionId() {
        return sessionId;
    }

    /** Called by the engine to retain a pending async operation. */
    public void retainAsyncWork() {}

    /** Called by the engine when an async operation completes. */
    public void releaseAsyncWork() {}

    // ── Sub-blueprint call depth ────────────────────────────────────────────

    /** Returns the current sub-blueprint nesting depth (0 = root execution). */
    public int callDepth() {
        return callDepth;
    }

    /** Increments the call depth before entering a sub-blueprint. */
    public void incrementCallDepth() {
        callDepth++;
    }

    /** Decrements the call depth after returning from a sub-blueprint. */
    public void decrementCallDepth() {
        if (callDepth > 0) callDepth--;
    }

    // ── Loop break signal ───────────────────────────────────────────────────

    /** Signals that the current loop should stop iterating. */
    public void requestBreak() {
        breakRequested = true;
    }

    /** Returns {@code true} if {@link #requestBreak()} has been called and not yet cleared. */
    public boolean isBreakRequested() {
        return breakRequested;
    }

    /** Clears the break signal after a loop node consumes it. */
    public void clearBreakRequest() {
        breakRequested = false;
    }

    /** Called by the engine when the synchronous part of the execution has finished. */
    public void closeRootExecution() {}
}
