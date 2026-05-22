package fr.blueprynt.model;

/**
 * A declared variable definition — scope, key, type, optional default.
 * Passed to {@link fr.blueprynt.api.BluepryntApi#declareVariable} and returned
 * by {@link fr.blueprynt.engine.ExecutionContext#variableDeclarations()}.
 */
public record BlueprintVariableData(
        String key,
        String type,
        VarScope scope,
        Object defaultValue,
        boolean persistent
) {}
