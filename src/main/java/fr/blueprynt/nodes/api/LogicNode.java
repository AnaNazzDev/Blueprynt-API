package fr.blueprynt.nodes.api;

import fr.blueprynt.engine.ExecutionContext;

public interface LogicNode extends NodeSpec {
    String selectExec(ExecutionContext ctx, NodeData config);
}
