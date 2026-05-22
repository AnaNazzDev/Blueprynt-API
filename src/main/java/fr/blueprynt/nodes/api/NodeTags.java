package fr.blueprynt.nodes.api;

/**
 * Well-known tag constants for {@link NodeSpec#getNodeTags()} and
 * {@link TriggerNode#getNodeTags()}.
 * Consumed by the frontend for behaviour such as "is this a variable node?".
 */
public final class NodeTags {
    private NodeTags() {}

    /** Node reads or writes a blueprint variable (shown in variable token picker). */
    public static final String VARIABLE = "variable";

    /** Node writes to a variable (subset of VARIABLE; enables type-pattern inference). */
    public static final String VARIABLE_WRITER = "variable_writer";

    /** Node performs a collection mutation (list/map add, remove, put…). */
    public static final String COLLECTION_OPERATION = "collection_op";

    /** Pure data node — no exec handles, evaluated lazily. */
    public static final String VALUE = "value";

    /** Acts as a cycle guard; the engine does not track back-edges through this node. */
    public static final String CYCLE_GUARD = "cycle_guard";
}
