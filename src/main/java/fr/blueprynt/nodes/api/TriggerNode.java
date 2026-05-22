package fr.blueprynt.nodes.api;

import fr.blueprynt.alerts.AlertRule;
import fr.blueprynt.engine.ExecutionContext;
import org.bukkit.event.Event;

import java.util.List;
import java.util.Set;

public interface TriggerNode {
    Set<Class<? extends Event>> getListenedEvents();

    boolean matches(Event event, NodeData config);

    ExecutionContext buildContext(Event event, NodeData config);

    List<OutputHandle> getOutputHandles();

    default List<NodeConfigField> getConfigFields() { return List.of(NodeConfigFields.cancelEvent()); }

    /**
     * Validation rules specific to this trigger, evaluated during blueprint validation.
     */
    default List<AlertRule> getAlertRules() { return List.of(); }

    /** @see NodeSpec#getNodeTags() */
    default List<String> getNodeTags() { return List.of(); }

    /**
     * Text-substitution tokens this trigger adds to the execution context
     * (e.g. {@code {player.name}}, {@code {chat_message}}).
     * Shown in the token-insert picker for text config fields.
     */
    default List<ContextToken> getContextTokens() { return List.of(); }
}
