package fr.blueprynt.nodes.api;

/**
 * Backward-compatible alias for {@link SelectableExecNode}, restricted to trigger nodes
 * by convention. New code should implement {@link SelectableExecNode} directly.
 *
 * @deprecated Use {@link SelectableExecNode} instead.
 */
@Deprecated
public interface SelectableExecTrigger extends SelectableExecNode {
    // Inherits selectExecHandle(ExecutionContext) from SelectableExecNode.
}
