package fr.blueprynt.alerts;

import java.util.function.Predicate;

public record AlertRule(
        String ruleId,
        AlertLevel level,
        String title,
        String description,
        Predicate<AlertContext> condition
) {
    public static AlertRule error(String id, String title, String description, Predicate<AlertContext> condition) {
        return new AlertRule(id, AlertLevel.ERROR, title, description, condition);
    }

    public static AlertRule warning(String id, String title, String description, Predicate<AlertContext> condition) {
        return new AlertRule(id, AlertLevel.WARNING, title, description, condition);
    }

    public static AlertRule info(String id, String title, String description, Predicate<AlertContext> condition) {
        return new AlertRule(id, AlertLevel.INFO, title, description, condition);
    }
}
