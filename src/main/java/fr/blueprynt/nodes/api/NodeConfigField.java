package fr.blueprynt.nodes.api;

import java.util.List;
import java.util.Map;

public record NodeConfigField(
        String key,
        String label,
        NodeConfigKind kind,
        String defaultValue,
        String placeholder,
        boolean required,
        List<String> options,
        NodeConfigTokenSupport tokenSupport,
        String inputHandle,
        NodeConfigConnectedBehavior connectedBehavior,
        Map<String, Object> uiHints
) {
    public NodeConfigField {
        options = options == null ? List.of() : List.copyOf(options);
        tokenSupport = tokenSupport == null ? NodeConfigTokenSupport.NONE : tokenSupport;
        connectedBehavior = connectedBehavior == null ? NodeConfigConnectedBehavior.DISABLE : connectedBehavior;
        uiHints = uiHints == null ? Map.of() : Map.copyOf(uiHints);
    }

    public static Builder builder(String key, String label, NodeConfigKind kind) {
        return new Builder(key, label, kind);
    }

    public NodeConfigField linked(String inputHandle) {
        return new NodeConfigField(key, label, kind, defaultValue, placeholder, required, options, tokenSupport, inputHandle, connectedBehavior, uiHints);
    }

    public NodeConfigField tokens(NodeConfigTokenSupport support) {
        return new NodeConfigField(key, label, kind, defaultValue, placeholder, required, options, support, inputHandle, connectedBehavior, uiHints);
    }

    public NodeConfigField uiHint(String key, Object value) {
        return toBuilder().uiHint(key, value).build();
    }

    /** Type-safe overload — prefer this over the raw {@code String} version. */
    public NodeConfigField uiHint(UiHint hint, Object value) {
        return uiHint(hint.key(), value);
    }

    public NodeConfigField uiHint(Map<String, Object> hints) {
        return toBuilder().uiHints(hints).build();
    }

    private Builder toBuilder() {
        Builder builder = new Builder(key, label, kind)
                .defaultValue(defaultValue)
                .placeholder(placeholder)
                .options(options.toArray(String[]::new))
                .tokenSupport(tokenSupport)
                .connectedBehavior(connectedBehavior)
                .uiHints(uiHints);
        if (required) {
            builder.required();
        }
        if (inputHandle != null) {
            builder.inputHandle(inputHandle);
        }
        return builder;
    }

    public static final class Builder {
        private final String key;
        private final String label;
        private final NodeConfigKind kind;
        private String defaultValue = "";
        private String placeholder = "";
        private boolean required;
        private List<String> options = List.of();
        private java.util.function.Supplier<List<String>> optionsSupplier;
        private NodeConfigTokenSupport tokenSupport = NodeConfigTokenSupport.NONE;
        private String inputHandle;
        private NodeConfigConnectedBehavior connectedBehavior = NodeConfigConnectedBehavior.DISABLE;
        private Map<String, Object> uiHints = Map.of();

        private Builder(String key, String label, NodeConfigKind kind) {
            this.key = key;
            this.label = label;
            this.kind = kind;
        }

        public Builder defaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public Builder placeholder(String placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public Builder required() {
            this.required = true;
            return this;
        }

        public Builder options(String... options) {
            this.options = List.of(options);
            return this;
        }

        /**
         * Provides options lazily — called every time {@link #build()} is invoked so the
         * returned list is always up-to-date when the node definition is serialized.
         * Takes precedence over any static options set via {@link #options}.
         */
        public Builder optionsSupplier(java.util.function.Supplier<List<String>> supplier) {
            this.optionsSupplier = supplier;
            return this;
        }

        public Builder tokenSupport(NodeConfigTokenSupport tokenSupport) {
            this.tokenSupport = tokenSupport;
            return this;
        }

        public Builder inputHandle(String inputHandle) {
            this.inputHandle = inputHandle;
            return this;
        }

        public Builder connectedBehavior(NodeConfigConnectedBehavior connectedBehavior) {
            this.connectedBehavior = connectedBehavior;
            return this;
        }

        public Builder uiHint(String key, Object value) {
            java.util.LinkedHashMap<String, Object> hints = new java.util.LinkedHashMap<>(uiHints);
            if (value == null) {
                hints.remove(key);
            } else {
                hints.put(key, value);
            }
            this.uiHints = Map.copyOf(hints);
            return this;
        }

        /** Type-safe overload — prefer this over the raw {@code String} version. */
        public Builder uiHint(UiHint hint, Object value) {
            return uiHint(hint.key(), value);
        }

        public Builder uiHints(Map<String, Object> uiHints) {
            this.uiHints = uiHints == null ? Map.of() : Map.copyOf(uiHints);
            return this;
        }

        public NodeConfigField build() {
            List<String> resolvedOptions = optionsSupplier != null
                    ? List.copyOf(optionsSupplier.get())
                    : options;
            return new NodeConfigField(key, label, kind, defaultValue, placeholder, required, resolvedOptions, tokenSupport, inputHandle, connectedBehavior, uiHints);
        }
    }
}
