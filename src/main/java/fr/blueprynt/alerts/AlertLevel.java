package fr.blueprynt.alerts;

public enum AlertLevel {
    ERROR,
    WARNING,
    INFO;

    public static AlertLevel fromValue(String value) {
        for (AlertLevel level : values()) {
            if (level.name().equalsIgnoreCase(value)) return level;
        }
        throw new IllegalArgumentException("Unknown AlertLevel: " + value);
    }
}
