package fr.blueprynt.nodes.api;

import fr.blueprynt.engine.ExecutionContext;

import java.util.List;
import java.util.Map;

public interface ConditionNode extends ValueNode {
    boolean evaluate(ExecutionContext ctx, NodeData config);

    default boolean evaluateResolved(ExecutionContext ctx, NodeData config) {
        boolean value = evaluate(ctx, config);
        return config.getBoolean("inverse", false) ? !value : value;
    }

    @Override
    default Map<String, Object> evaluateValues(ExecutionContext ctx, NodeData config) {
        return Map.of("result", evaluateResolved(ctx, config));
    }

    @Override
    default List<NodeConfigField> getConfigFields() {
        return List.of(NodeConfigFields.inverse());
    }
}
