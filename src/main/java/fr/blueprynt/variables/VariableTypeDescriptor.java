package fr.blueprynt.variables;

import fr.blueprynt.model.VarType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public record VariableTypeDescriptor(
        String id,
        String label,
        String handleType,
        Predicate<Object> assignableFrom,
        Function<Object, String> toText,
        Function<String, Object> deserializer,
        Function<Object, String> serializer,
        List<VariableProperty> properties,
        /** Optional CSS hex color for handle dots / token icons in the frontend editor. */
        String handleColor,
        /**
         * Whether this type appears in user-facing pickers ({@code true}, the default) or is
         * registered only for metadata purposes — colour, handle wiring — but hidden from
         * the Variables panel and TYPE_SELECT config fields ({@code false}).
         */
        boolean visible
) {
    public boolean serializable() {
        return serializer != null && deserializer != null;
    }

    public static Builder builder(String id, String label) {
        return new Builder(id, label);
    }

    public static final class Builder {
        private final String id;
        private final String label;
        private String handleType = VarType.OBJECT.name();
        private Predicate<Object> assignableFrom = value -> true;
        private Function<Object, String> toText = value -> value == null ? "" : String.valueOf(value);
        private Function<String, Object> deserializer;
        private Function<Object, String> serializer;
        private final List<VariableProperty> properties = new ArrayList<>();
        private String handleColor;
        private boolean visible = true;

        private Builder(String id, String label) {
            this.id = id;
            this.label = label;
        }

        public Builder handleType(String handleType) {
            this.handleType = handleType;
            return this;
        }

        /** Convenience overload — accepts the {@link VarType} enum. */
        public Builder handleType(VarType handleType) {
            this.handleType = handleType.name();
            return this;
        }

        public Builder assignableFrom(Predicate<Object> assignableFrom) {
            this.assignableFrom = assignableFrom;
            return this;
        }

        public Builder assignableFrom(Class<?> type) {
            this.assignableFrom = type::isInstance;
            return this;
        }

        public Builder toText(Function<Object, String> toText) {
            this.toText = toText;
            return this;
        }

        public Builder serializer(Function<Object, String> serializer) {
            this.serializer = serializer;
            return this;
        }

        public Builder deserializer(Function<String, Object> deserializer) {
            this.deserializer = deserializer;
            return this;
        }

        public Builder codec(Function<Object, String> serializer, Function<String, Object> deserializer) {
            this.serializer = serializer;
            this.deserializer = deserializer;
            return this;
        }

        public Builder property(String id, String label, String typeId, Function<Object, Object> getter) {
            properties.add(new VariableProperty(id, label, typeId, getter));
            return this;
        }

        public Builder property(VariableProperty property) {
            properties.add(property);
            return this;
        }

        public Builder handleColor(String handleColor) {
            this.handleColor = handleColor;
            return this;
        }

        /**
         * Marks this type as invisible in user-facing pickers. Use for structural /
         * internal types (EXEC, LIST, MAP, OBJECT) that are needed for colour metadata
         * but must not appear in the Variables panel or TYPE_SELECT fields.
         */
        public Builder visible(boolean visible) {
            this.visible = visible;
            return this;
        }

        public VariableTypeDescriptor build() {
            return new VariableTypeDescriptor(id, label, handleType, assignableFrom, toText,
                    deserializer, serializer, List.copyOf(properties), handleColor, visible);
        }
    }
}
