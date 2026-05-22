package fr.blueprynt.model;

import java.util.Map;

/** Serialised representation of a single node in a blueprint graph. */
public record BlueprintNodeData(
        String id,
        String type,
        Position position,
        Map<String, Object> data
) {
    public BlueprintNodeData {
        data = data == null ? Map.of() : Map.copyOf(data);
    }
}
