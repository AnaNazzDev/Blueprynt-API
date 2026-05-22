package fr.blueprynt.variables;

import java.util.Collection;
import java.util.Optional;

/**
 * Stub — the real implementation is provided at runtime by the Blueprynt plugin.
 * This class exists so addon node code (e.g. {@link fr.blueprynt.nodes.api.TypeFlexibleNode})
 * compiles against it.
 *
 * <p>All methods throw {@link UnsupportedOperationException} at runtime if Blueprynt
 * is not installed.</p>
 */
public class VariableTypeRegistry {

    /** Returns the descriptor for the given type ID, or empty if not registered. */
    public Optional<VariableTypeDescriptor> get(String typeId) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /** Returns {@code true} if the given type ID (including compound types like {@code LIST<INT>}) is registered. */
    public boolean exists(String typeId) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /** Returns {@code true} if the given type ID is registered and has a codec (serializer + deserializer). */
    public boolean serializable(String typeId) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /** Returns the handle type for the given type ID (e.g. {@code "OBJECT"} for collection types). */
    public String handleType(String typeId) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /** Returns all registered type descriptors. */
    public Collection<VariableTypeDescriptor> all() {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /** Converts a value to its text representation using the registered serializer for {@code typeId}. */
    public String toText(String typeId, Object value) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /** Returns the value of a property on a typed variable value. */
    public Object property(String typeId, Object value, String property) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /** Returns the type ID of a property on a typed variable. */
    public String propertyType(String typeId, String property) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /** Registers a custom variable type from a descriptor. */
    public void register(VariableTypeDescriptor descriptor) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }

    /** Registers a custom variable type from a {@link VariableType} implementation. */
    public void register(VariableType type) {
        throw new UnsupportedOperationException("Blueprynt not installed");
    }
}
