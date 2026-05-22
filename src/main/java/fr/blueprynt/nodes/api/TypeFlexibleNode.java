package fr.blueprynt.nodes.api;

import fr.blueprynt.variables.VariableTypeRegistry;

/**
 * Optional hook for nodes whose handle compatibility depends on their config.
 * Example: Set Variable's value input should match the selected variable type.
 */
public interface TypeFlexibleNode {
    boolean handlesCompatible(String targetHandle, String sourceType, NodeData config, VariableTypeRegistry variableTypes);

    /**
     * The handle ID whose type compatibility is determined dynamically.
     * Returns {@code null} when the node does not expose a single canonical flexible handle.
     */
    default String getTypeFlexibleHandle() { return null; }
}
