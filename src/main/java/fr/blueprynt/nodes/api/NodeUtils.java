package fr.blueprynt.nodes.api;

public class NodeUtils {
    public static String normalizeCommandName(String rawCommand) {
        if (rawCommand == null || rawCommand.isBlank()) {
            return "";
        }

        String command = rawCommand.trim().split("\\s+")[0].replaceFirst("^/", "");

        int namespaceIndex = command.indexOf(':');
        if (namespaceIndex >= 0 && namespaceIndex < command.length() - 1) {
            command = command.substring(namespaceIndex + 1);
        }

        return command;
    }
}
