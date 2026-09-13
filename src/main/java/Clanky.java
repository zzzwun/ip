import java.util.Scanner;
import java.util.ArrayList;

public class Clanky {
    public static void main(String[] args) throws ClankyException {
        // Declare Strings
        String banner = "  ____   _          _      _   _   _  __ __   __\n"
                + " / ___| | |        / \\    | \\ | | | |/ / \\ \\ / /\n"
                + "| |     | |       / _ \\   |  \\| | | ' /   \\ V / \n"
                + "| |___  | |___   / ___ \\  | |\\  | | . \\    | |  \n"
                + " \\____| |_____| /_/   \\_\\ |_| \\_| |_|\\_\\   |_|";

        String divider = "=================================================";

        // Init Print Statements
        System.out.println(banner);
        System.out.println(divider);
        System.out.println("Hello! I'm Clanky.");
        System.out.println("What can I do for you ?");
        System.out.println(divider);

        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);
        String input = "";

        // Initialize ArrayList
        ArrayList<Task> list = new ArrayList<>();

        // Scan for Input
        label:
        while (true) {
            // Gets Input
            input = scanner.nextLine();

            // Splits Up Input Into Command & Arguments
            String[] parts = input.trim().split("\\s+", 2);
            String command = parts[0];

            // Input 'bye' Command
            try {
                switch (command) {
                    case "bye":
                        break label;

                    // Input 'list' Command
                    case "list": {
                        System.out.println("\t" + divider);
                        if (list.isEmpty()) {
                            System.out.println("\tNo Tasks Yet");
                        }
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println("\t" + (i + 1) + ". " + list.get(i));
                        }
                        System.out.println("\t" + divider);
                        continue;
                    }

                    // Input 'mark' Command
                    case "mark": {
                        int index = getIndex(parts, list);
                        list.get(index).setMark();
                        System.out.println("\t" + divider);
                        System.out.println("\tNice! I've marked this task as done:");
                        System.out.println("\t" + list.get(index));
                        System.out.println("\t" + divider);
                        continue;
                    }

                    // Input 'unmark' Command
                    case "unmark": {
                        int index = getIndex(parts, list);
                        list.get(index).setUnmark();
                        System.out.println("\t" + divider);
                        System.out.println("\tOK, I've marked this task as not done yet:");
                        System.out.println("\t" + list.get(index));
                        System.out.println("\t" + divider);
                        continue;
                    }

                    // Input 'to-do' command
                    case "todo": {
                        // Catch Missing Argument
                        if (parts.length < 2 || parts[1].isBlank()) {
                            throw new ClankyException("Description of ToDo can't be empty.");
                        }

                        // Instantiate new Task
                        Task task = new Todo(parts[1].trim());
                        list.add(task);
                        printAddedTask(task, list.size(), divider);
                        continue;
                    }

                    // Input 'deadline' command
                    case "deadline": {
                        // Catch Wrong Formatting
                        if (parts.length < 2 || !parts[1].contains("/by")) {
                            throw new ClankyException("Use the format: deadline <description> /by <time>");
                        }
                        String[] deadlineParts = parts[1].split("/by", 2);
                        String desc = deadlineParts[0].trim();
                        String by = deadlineParts[1].trim();

                        // Catch Empty Arguments
                        if (desc.isEmpty() || by.isEmpty()) {
                            throw new ClankyException("Description and By are required.");
                        }

                        // Instantiate new Task
                        Task task = new Deadline(desc, by);
                        list.add(task);
                        printAddedTask(task, list.size(), divider);
                        continue;
                    }

                    // Input 'event' command
                    case "event": {
                        // Catch Wrong Formatting
                        if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
                            throw new ClankyException("Use the format: event <description> /from <start> /to <end>");
                        }
                        String[] fromSplit = parts[1].split("/from", 2);
                        String desc = fromSplit[0].trim();
                        String[] toSplit = fromSplit[1].split("/to", 2);
                        String from = toSplit[0].trim();
                        String to = toSplit[1].trim();

                        // Catch Empty Arguments
                        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                            throw new ClankyException("Description, From, and To are required.");
                        }

                        // Instantiate new Task
                        Task task = new Event(desc, from, to);
                        list.add(task);
                        printAddedTask(task, list.size(), divider);
                        continue;
                    }

                    // Default case
                    default: {
                        throw new ClankyException("Idk what's that command.");
                    }

                }
            } catch (ClankyException e){
                System.out.println("\t" + divider);
                System.out.println("\t" + e.getMessage());
                System.out.println("\t" + divider);
            }

        }

        // Close Scanner
        System.out.println("\t" + divider);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t" + divider);
        scanner.close();
    }

    // HELPER FUNCTIONS
    private static int getIndex(String[] parts, ArrayList<Task> list) throws ClankyException {
        // Catch Missing Arguments
        if (parts.length < 2) {
            throw new ClankyException("Please specify a Task Number.");
        }

        int index;
        // Catch Invalid Task Number
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch(NumberFormatException e) {
            throw new ClankyException("That's not a valid Task number.");
        }

        // Catch Index Out Of Array
        if (index < 0 || index >= list.size()) {
            throw new ClankyException("That Task Number does not exist.");
        }
        return index;
    }

    private static void printAddedTask(Task task, int listSize, String divider) {
        System.out.println("\t" + divider);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + listSize + " task" + (listSize == 1 ? "" : "s") + " in the list.");
        System.out.println("\t" + divider);
    }
}
