package clanky;

import clanky.task.TaskList;

public class Parser {

    public static String[] parse(String input) {
        return input.trim().split("\\s+", 2);
    }

    public static int parseIndex(String[] parts, TaskList tasks, String action) throws ClankyException {
        // Catch Missing Arguments
        if (parts.length < 2) {
            throw new ClankyException("Please specify a Task Number to " + action + ".");
        }

        int index;
        // Catch Invalid Task Number
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch(NumberFormatException e) {
            throw new ClankyException("That's not a valid Task number.");
        }

        // Catch Index Out Of Array
        if (index < 0 || index >= tasks.size()) {
            throw new ClankyException("That Task Number does not exist.");
        }
        return index;
    }

    public static String parseTodoArgs(String args) throws ClankyException {
        if (args == null || args.isBlank()) {
            throw new ClankyException("Description of ToDo can't be empty.");
        }
        return args.trim();
    }

    public static String[] parseDeadlineArgs(String args) throws ClankyException {
        if (args == null || !args.contains("/by")) {
            throw new ClankyException("Please use the format: deadline <description> /by <time>");
        }
        String[] deadlineParts = args.split("/by", 2);
        String desc = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();
        if (desc.isBlank() || by.isBlank()) {
            throw new ClankyException("Both description and /by time are required.");
        }
        return new String[]{desc, by};
    }

    public static String[] parseEventArgs(String args) throws ClankyException {
        if (args == null || !args.contains("/from") || !args.contains("/to")) {
            throw new ClankyException("Please use the format: event <description> /from <start> /to <end>");
        }
        String[] fromSplit = args.split("/from", 2);
        String desc = fromSplit[0].trim();
        String[] toSplit = fromSplit[1].split("/to", 2);
        String from = toSplit[0].trim();
        String to = toSplit[1].trim();
        if (desc.isBlank() || from.isBlank() || to.isBlank()) {
            throw new ClankyException("Description, /from, and /to are all required.");
        }
        return new String[]{desc, from, to};
    }


}
