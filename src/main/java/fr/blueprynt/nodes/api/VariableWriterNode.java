package fr.blueprynt.nodes.api;

/**
 * Extension of {@link VariableNode} for nodes that declare/write a variable.
 * Used by the alert engine to build the declared-variable type map for token
 * validation without hardcoding node IDs.
 */
public interface VariableWriterNode extends VariableNode {
    /** Scope of the variable being written, resolved from the node's config. */
    String getWrittenScope(NodeData config);

    /** Key of the variable being written. */
    String getWrittenKey(NodeData config);

    /** Type ID of the variable being written (e.g. {@code "STRING"}, {@code "LIST<INT>"}). */
    String getWrittenTypeId(NodeData config);

    /**
     * Optional type-pattern template so the frontend can compute the variable
     * type expression from node config without hardcoding node IDs.
     * <p>
     * Placeholders are config-field keys wrapped in braces, e.g.
     * {@code "LIST<{elementType}>"} for Create List or
     * {@code "MAP<{keyType},{valueType}>"} for Create Map.
     * Returns {@code null} for nodes whose type comes directly from a {@code type}
     * config field (the default case).
     */
    default String getWrittenTypePattern() { return null; }
}
