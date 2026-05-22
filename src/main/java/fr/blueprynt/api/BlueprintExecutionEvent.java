package fr.blueprynt.api;

import org.bukkit.entity.Player;

public record BlueprintExecutionEvent(
        String blueprintId,
        String blueprintName,
        Player triggerPlayer,
        long durationMs,
        boolean succeeded,
        boolean budgetExceeded,
        boolean maxStepsExceeded
) {
}
