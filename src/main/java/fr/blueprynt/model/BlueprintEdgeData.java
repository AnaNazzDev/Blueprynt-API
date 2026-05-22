package fr.blueprynt.model;

/** Serialised representation of a directed edge between two node handles. */
public record BlueprintEdgeData(
        String id,
        String source,
        String sourceHandle,
        String target,
        String targetHandle
) {}
