package fr.blueprynt.nodes.api;

import fr.blueprynt.engine.ExecutionContext;

public interface ToggleNode extends InstantTriggerableNode {
    boolean requiresMainThread();

    void enable(ExecutionContext ctx, NodeData config);
    void disable(ExecutionContext ctx, NodeData config);
}
