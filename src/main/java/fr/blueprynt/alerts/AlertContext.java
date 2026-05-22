package fr.blueprynt.alerts;

import fr.blueprynt.engine.NodeRegistry;
import fr.blueprynt.model.BlueprintData;
import fr.blueprynt.model.BlueprintNodeData;

/**
 * Context passed to each {@link AlertRule} during blueprint validation.
 * Gives the rule access to the current node, the full blueprint graph, and the registry.
 */
public record AlertContext(
        String nodeId,
        String nodeTypeId,
        BlueprintNodeData nodeData,
        BlueprintData blueprint,
        NodeRegistry registry
) {
    /** Convenience: read a config value from the current node as a String. */
    public String config(String key) {
        Object v = nodeData.data().get(key);
        return v == null ? "" : String.valueOf(v);
    }
}
