package fr.blueprynt.variables;

import fr.blueprynt.model.VarType;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface VariableType {
    String id();

    String label();

    default String handleType() {
        return VarType.OBJECT.name();
    }

    Predicate<Object> assignableFrom();

    Function<Object, String> toText();

    default Function<String, Object> deserializer() {
        return null;
    }

    default Function<Object, String> serializer() {
        return null;
    }

    default List<VariableProperty> properties() {
        return List.of();
    }

    /**
     * CSS hex color used to tint handle dots and token icons for this type in the
     * frontend editor.  Returns {@code null} to fall back to the built-in palette.
     */
    default String handleColor() { return null; }

    default VariableTypeDescriptor descriptor() {
        return new VariableTypeDescriptor(id(), label(), handleType(), assignableFrom(), toText(),
                deserializer(), serializer(), properties(), handleColor(), true);
    }
}
