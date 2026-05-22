package fr.blueprynt.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/** Full serialised blueprint: nodes, edges, and metadata. */
public record BlueprintData(
        String id,
        String name,
        String description,
        boolean enabled,
        int version,
        Instant createdAt,
        Instant updatedAt,
        List<BlueprintNodeData> nodes,
        List<BlueprintEdgeData> edges,
        BlueprintMetadata metadata
) {
    public BlueprintData {
        if (nodes    == null) nodes    = new ArrayList<>();
        if (edges    == null) edges    = new ArrayList<>();
        if (metadata == null) metadata = BlueprintMetadata.defaults();
    }

    public static BlueprintData empty(String id, String name) {
        Instant now = Instant.now();
        return new BlueprintData(id, name, "", true, 1, now, now, List.of(), List.of(), BlueprintMetadata.defaults());
    }
}
