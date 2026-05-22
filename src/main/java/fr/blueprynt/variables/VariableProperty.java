package fr.blueprynt.variables;

import java.util.function.Function;

public record VariableProperty(String id, String label, String typeId, Function<Object, Object> getter) {
}
