package fr.blueprynt.nodes.api;

import java.util.List;

/**
 * Marker + contract for nodes whose input/output handles depend on their config.
 *
 * <p>The no-arg {@code getInputHandles()} / {@code getOutputHandles()} methods
 * inherited from {@link NodeSpec} or {@link TriggerNode} should return the
 * <em>base</em> handles (i.e. the handles that exist when the config is empty).
 * These are used as a fallback for catalog display and initial node placement.</p>
 */
public interface DynamicHandleNode {

    /**
     * Returns the input handles for this node given its current config.
     * Called fresh on each API request and on every config change.
     */
    List<InputHandle> getInputHandles(NodeData config);

    /**
     * Returns the output handles for this node given its current config.
     * Called fresh on each API request and on every config change.
     */
    List<OutputHandle> getOutputHandles(NodeData config);
}
