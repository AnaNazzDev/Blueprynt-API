package fr.blueprynt.api;

import fr.blueprynt.model.VarScope;
import fr.blueprynt.variables.VariableType;
import fr.blueprynt.variables.VariableTypeDescriptor;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Public entry point for third-party plugins integrating with Blueprynt.
 *
 * <p>This is a <strong>stub</strong> — all methods throw
 * {@link UnsupportedOperationException} if the Blueprynt plugin is not installed.
 * At runtime, the real implementation is provided by the Blueprynt JAR, which
 * replaces these stubs with the actual code.</p>
 *
 * <h3>Usage (in your plugin's {@code onEnable()})</h3>
 * <pre>{@code
 * if (Bukkit.getPluginManager().getPlugin("Blueprynt") != null) {
 *     BluepryntApi.registerNode(new MyCustomNode());
 *     BluepryntApi.registerVariableType(MyVariableType.DESCRIPTOR);
 * }
 * }</pre>
 *
 * <h3>Dependency (Gradle)</h3>
 * <pre>{@code
 * repositories { maven { url "https://jitpack.io" } }
 * dependencies { compileOnly("com.github.AnaNazzDev:Blueprynt-API:1.0.0") }
 * }</pre>
 *
 * <p>This class is stable API. All other Blueprynt packages are internal.</p>
 */
public final class BluepryntApi {

    private BluepryntApi() {}

    /**
     * Registers a custom node with Blueprynt.
     *
     * <p>The node class must be annotated with
     * {@link fr.blueprynt.nodes.api.BlueprintNode} and implement one of the node
     * interfaces ({@link fr.blueprynt.nodes.api.TriggerNode},
     * {@link fr.blueprynt.nodes.api.ActionNode}, etc.).</p>
     *
     * @param node the node instance to register
     * @throws UnsupportedOperationException if Blueprynt is not installed
     * @throws IllegalStateException         if called before Blueprynt is enabled
     * @throws IllegalArgumentException      if the node is missing {@code @BlueprintNode}
     */
    public static void registerNode(Object node) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /**
     * Unregisters a custom node by id and asynchronously revalidates blueprints.
     * Intended for use during your plugin's {@code onDisable()}.
     *
     * @param nodeId node id originally registered with {@link #registerNode(Object)}
     * @return {@code true} when a node existed and was removed
     * @throws UnsupportedOperationException if Blueprynt is not installed
     */
    public static boolean unregisterNode(String nodeId) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /**
     * Reloads all stored blueprints asynchronously.
     * Useful after multiple registrations to force an immediate refresh.
     *
     * @throws UnsupportedOperationException if Blueprynt is not installed
     */
    public static CompletableFuture<Void> revalidateBlueprints() {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /**
     * Registers a custom variable type from a {@link VariableTypeDescriptor}.
     *
     * @param descriptor the descriptor built with {@link VariableTypeDescriptor#builder(String, String)}
     * @throws UnsupportedOperationException if Blueprynt is not installed
     */
    public static void registerVariableType(VariableTypeDescriptor descriptor) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /**
     * Registers a custom variable type from a {@link VariableType} implementation.
     *
     * @param type the variable type to register
     * @throws UnsupportedOperationException if Blueprynt is not installed
     */
    public static void registerVariableType(VariableType type) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /**
     * Programmatically declares a persistent variable, merging it into the server-wide
     * declaration registry.
     *
     * <p>If a declaration with the same scope + key already exists it is silently replaced.
     * The updated list is persisted asynchronously.</p>
     *
     * @param scope        {@code GLOBAL} or {@code PLAYER} only
     * @param key          variable key (alphanumeric + underscores, max 64 chars)
     * @param typeId       a registered, serializable type (e.g. {@code "STRING"}, {@code "LIST<INT>"})
     * @param defaultValue optional default value string; may be {@code null}
     * @throws UnsupportedOperationException if Blueprynt is not installed
     * @throws IllegalArgumentException      if scope, key, or type is invalid
     */
    public static void declareVariable(VarScope scope, String key, String typeId, String defaultValue) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /**
     * Fires a custom trigger by ID, optionally with a player and context data.
     * Blueprints listening for this trigger ID will execute.
     *
     * @param triggerId trigger ID to fire (matches the {@code triggerId} config field
     *                  on {@code core:instant_trigger} nodes)
     * @param player    triggering player (may be {@code null} for server-side triggers)
     * @param data      additional data injected into the execution context (may be empty)
     * @throws UnsupportedOperationException if Blueprynt is not installed
     */
    public static void fireTrigger(String triggerId, Player player, Map<String, Object> data) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /**
     * Registers a listener that is called after each blueprint execution completes.
     *
     * @param listener the listener to register
     * @return an {@link AutoCloseable} that unregisters the listener when closed
     * @throws UnsupportedOperationException if Blueprynt is not installed
     */
    public static AutoCloseable onBlueprintExecuted(Consumer<BlueprintExecutionEvent> listener) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }
}
