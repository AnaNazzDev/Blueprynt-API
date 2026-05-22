package fr.blueprynt.nodes.api;

import java.util.List;

public record NodeDefinition(
        String id,
        String label,
        String category,
        NodeType nodeType,
        String color,
        String icon,
        String description,
        List<InputHandle> inputs,
        List<OutputHandle> outputs,
        List<NodeConfigField> configFields,
        boolean available,
        List<String> requiredHooks,
        List<String> tags,
        String typeFlexibleHandle,
        String writerTypePattern,
        List<ContextToken> contextTokens,
        boolean dynamicHandles,
        String requiresSiblingType,
        String requiresSiblingLabel
) {
}
