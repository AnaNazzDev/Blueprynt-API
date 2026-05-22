package fr.blueprynt.nodes.api;

import fr.blueprynt.engine.ExecutionContext;

public interface InstantTriggerableNode {
    default ExecutionContext buildContext(String blueprintId, NodeData config) {
        return new ExecutionContext(blueprintId, null);
    }
}
