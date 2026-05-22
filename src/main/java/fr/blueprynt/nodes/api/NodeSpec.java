package fr.blueprynt.nodes.api;

import fr.blueprynt.alerts.AlertRule;

import java.util.List;

/**
 * Base interface carried by all non-trigger node types (Action, Condition, Logic).
 * <p>
 * {@link TriggerNode} intentionally does <em>not</em> extend {@code NodeSpec}
 * because triggers have no input handles.
 */
public interface NodeSpec {
    List<InputHandle> getInputHandles();
    List<OutputHandle> getOutputHandles();

    default List<NodeConfigField> getConfigFields() { return List.of(); }

    /**
     * Validation rules specific to this node, evaluated during blueprint validation.
     */
    default List<AlertRule> getAlertRules() { return List.of(); }

    /**
     * Semantic tags exposed in {@link NodeDefinition} and consumed by the frontend.
     * Use the constants in {@link NodeTags} for well-known values.
     */
    default List<String> getNodeTags() { return List.of(); }
}
