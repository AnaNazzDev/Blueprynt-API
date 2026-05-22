package fr.blueprynt.model;

import java.time.Instant;

/** A variable value as stored in the DB / variable cache. */
public record StoredVar(String value, String type, Instant updatedAt) {

    public static StoredVar of(String value, VarType type) {
        return of(value, type == null ? "STRING" : type.name());
    }

    public static StoredVar of(String value, String type) {
        return new StoredVar(value, type == null || type.isBlank() ? "STRING" : type, Instant.now());
    }
}
