package fr.blueprynt.nodes.api;

/**
 * Marker interface for nodes that mutate or query a collection variable
 * (list add/remove/contains, map put/get/remove/hasKey…).
 * {@link NodeTags#COLLECTION_OPERATION} is automatically added by the engine when
 * this interface is detected.
 */
public interface CollectionOperationNode extends VariableNode {
}
