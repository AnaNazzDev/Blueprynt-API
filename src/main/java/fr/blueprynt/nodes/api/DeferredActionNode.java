package fr.blueprynt.nodes.api;

import fr.blueprynt.engine.ExecutionContext;

public interface DeferredActionNode extends ActionNode {
    long getDelayTicks(ExecutionContext ctx, NodeData config);
}
