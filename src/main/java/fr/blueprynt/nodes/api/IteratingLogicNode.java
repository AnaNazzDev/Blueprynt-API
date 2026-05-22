package fr.blueprynt.nodes.api;

import fr.blueprynt.engine.ExecutionContext;

import java.util.List;
import java.util.Map;

public interface IteratingLogicNode extends LogicNode {
    List<Map<String, Object>> iterations(ExecutionContext ctx, NodeData config);

    default String loopHandle() {
        return "loop";
    }

    default String doneHandle() {
        return "done";
    }

    @Override
    default String selectExec(ExecutionContext ctx, NodeData config) {
        return doneHandle();
    }
}
