package fr.blueprynt.nodes.api;

import fr.blueprynt.engine.ExecutionContext;

public interface ActionNode extends NodeSpec {
    boolean requiresMainThread();

    void execute(ExecutionContext ctx, NodeData config);
}
