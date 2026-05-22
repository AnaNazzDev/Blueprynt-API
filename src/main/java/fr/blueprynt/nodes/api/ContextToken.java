package fr.blueprynt.nodes.api;

/**
 * A text-substitution token contributed by a {@link TriggerNode} to the
 * execution context (e.g. {@code {player.name}}).
 * Shown in the token-insert picker for text config fields in the frontend.
 */
public record ContextToken(
        /** Display label, e.g. {@code "player.name"}. */
        String label,
        /** Full token value, e.g. {@code "{player.name}"}. */
        String value
) {
    public static ContextToken of(String token) {
        return new ContextToken(token.replaceAll("[{}]", ""), token);
    }
}
