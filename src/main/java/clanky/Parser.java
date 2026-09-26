package clanky;

import clanky.task.Deadline;
import clanky.task.Event;
import clanky.task.TaskList;
import clanky.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

    public static Todo parseTodoArgs(String args) throws ClankyException {
        if (args == null || args.isBlank()) {
            throw new ClankyException("Description of ToDo can't be empty.");
        }
        return new Todo(args.trim());
    }

    public static Deadline parseDeadlineArgs(String args) throws ClankyException {
        if (args == null || !args.contains("/by")) {
            throw new ClankyException("Please use the format: deadline <description> /by <time>");
        }
        String[] deadlineParts = args.split("/by", 2);
        String desc = deadlineParts[0].trim();
        String byText = deadlineParts[1].trim();
        if (desc.isBlank() || byText.isBlank()) {
            throw new ClankyException("Both description and /by time are required.");
        }

        LocalDate by;
        try {
            by = LocalDate.parse(byText);
        } catch (DateTimeParseException e) {
            throw new ClankyException("Use yyyy-mm-dd format for dates, e.g. 2026-11-11");
        }
        return new Deadline(desc, by);
    }

    public static Event parseEventArgs(String args) throws ClankyException {
        if (args == null || !args.contains("/from") || !args.contains("/to")) {
            throw new ClankyException("Please use the format: event <description> /from <start> /to <end>");
        }
        String[] fromSplit = args.split("/from", 2);
        String desc = fromSplit[0].trim();
        String[] toSplit = fromSplit[1].split("/to", 2);
        String fromText = toSplit[0].trim();
        String toText = toSplit[1].trim();
        if (desc.isBlank() || fromText.isBlank() || toText.isBlank()) {
            throw new ClankyException("Description, /from, and /to are all required.");
        }

        LocalDate from;
        LocalDate to;
        try {
            from = LocalDate.parse(fromText);
            to = LocalDate.parse(toText);
        } catch (DateTimeParseException e) {
            throw new ClankyException("Use yyyy-mm-dd format for dates, e.g. 2026-11-11");
        }
        return new Event(desc, from, to);
    }

    public static String parseFindArgs(String args) throws ClankyException {
        if (args == null || args.isBlank()) {
            throw new ClankyException("Please specify a keyword to search for");
        }
        return args.trim();
    }

}
