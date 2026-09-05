import java.util.Scanner;
import java.util.ArrayList;

public class Clanky {
    public static void main(String[] args) {
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
                    int index = Integer.parseInt(parts[1]) - 1;
                    list.get(index).setMark();
                    System.out.println("\t" + divider);
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t" + list.get(index));
                    System.out.println("\t" + divider);
                    continue;
                }

                // Input 'unmark' Command
                case "unmark": {
                    int index = Integer.parseInt(parts[1]) - 1;
                    list.get(index).setUnmark();
                    System.out.println("\t" + divider);
                    System.out.println("\tOK, I've marked this task as not done yet:");
                    System.out.println("\t" + list.get(index));
                    System.out.println("\t" + divider);
                    continue;
                }

                // Input 'to-do' command
                case "todo": {
                    Task task = new Todo(parts[1].trim());
                    list.add(task);
                    printAddedTask(task, list.size(), divider);
                    continue;
                }

                // Input 'deadline' command
                case "deadline": {
                    String[] deadlineParts = parts[1].split("/by", 2);
                    String desc = deadlineParts[0].trim();
                    String by = deadlineParts[1].trim();
                    Task task = new Deadline(desc, by);
                    list.add(task);
                    printAddedTask(task, list.size(), divider);
                    continue;
                }

                // Input 'event' command
                case "event": {
                    String[] fromSplit = parts[1].split("/from", 2);
                    String desc = fromSplit[0].trim();
                    String[] toSplit = fromSplit[1].split("/to", 2);
                    String from = toSplit[0].trim();
                    String to = toSplit[1].trim();
                    Task task = new Event(desc, from, to);
                    list.add(task);
                    printAddedTask(task, list.size(), divider);
                    continue;
                }

                // Default case
                default: {
                    System.out.println("\t" + divider);
                    System.out.println("\tRe-Enter Valid Command");
                    System.out.println("\t" + divider);
                    continue;
                }

            }

        }

        // Close Scanner
        System.out.println("\t" + divider);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t" + divider);
        scanner.close();
    }

    private static void printAddedTask(Task task, int listSize, String divider) {
        System.out.println("\t" + divider);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + listSize + " task" + (listSize == 1 ? "" : "s") + " in the list.");
        System.out.println("\t" + divider);
    }
}
