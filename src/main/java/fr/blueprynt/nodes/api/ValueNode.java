package fr.blueprynt.nodes.api;

import fr.blueprynt.engine.ExecutionContext;

import java.util.Map;

/**
 * Pure data node evaluated lazily when another node reads one of its outputs.
 * Value nodes must not perform side effects or route execution flow.
 */
public interface ValueNode extends NodeSpec {
    Map<String, Object> evaluateValues(ExecutionContext ctx, NodeData config);
}
