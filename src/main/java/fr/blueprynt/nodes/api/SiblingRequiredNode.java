package fr.blueprynt.nodes.api;

/**
 * Marker interface for nodes whose exec input pin may only be wired when a
 * specific companion node type is present elsewhere in the same blueprint.
 *
 * <p>The constraint is declarative — exposed via {@link NodeDefinition#requiresSiblingType()}
 * and enforced generically in the frontend's connection validator.</p>
 */
public interface SiblingRequiredNode {

    /**
     * The node type ID that must be present in the blueprint.
     * Corresponds to {@link BlueprintNode#id()} of the required companion node.
     */
    String getRequiredSiblingType();

    /**
     * Human-readable name shown in the frontend error message when the sibling is
     * absent, e.g. {@code "Callable Trigger"}.
     */
    String getRequiredSiblingLabel();
}
