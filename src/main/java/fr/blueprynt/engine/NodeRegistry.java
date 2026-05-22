package fr.blueprynt.engine;

import fr.blueprynt.nodes.api.InputHandle;
import fr.blueprynt.nodes.api.NodeData;
import fr.blueprynt.nodes.api.OutputHandle;

/**
 * Stub — the real implementation is provided at runtime by the Blueprynt plugin.
 * This class exists so addon code compiles against it.
 */
public class NodeRegistry {

    /** Returns whether two handles are type-compatible for wiring. */
    public boolean handlesCompatible(InputHandle target, OutputHandle source) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /** Returns the handles for a given node type based on its current config. */
    public Object handlesForConfig(String nodeId, NodeData config) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }
}
