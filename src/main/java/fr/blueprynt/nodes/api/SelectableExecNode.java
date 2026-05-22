package fr.blueprynt.nodes.api;

import fr.blueprynt.engine.ExecutionContext;

/**
 * Mixin for any node that needs to dynamically choose which {@code exec} output
 * handle to follow at runtime based on the current {@link ExecutionContext}.
 *
 * <p>Can be combined with {@link TriggerNode}, {@link ActionNode}, {@link LogicNode},
 * or any other node contract.</p>
 */
public interface SelectableExecNode {
    /**
     * Returns the name of the output exec handle to follow for this execution.
     * Return {@code "exec"} as the safe fallback / no-match case.
     */
    String selectExecHandle(ExecutionContext ctx);
}
