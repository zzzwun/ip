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
        while (true) {
            // Gets Input
            input = scanner.nextLine();

            // Splits Up Input Into Command & Arguments
            String[] parts = input.trim().split("\\s+", 2);
            String command = parts[0];

            // Input 'bye' Command
            if (command.equals("bye")) {
                break;
            }

            // Input 'list' Command
            if (command.equals("list")) {
                System.out.println("\t" + divider);
                if (list.isEmpty()) {
                    System.out.println("\tNo Tasks Yet");
                }
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t" + (i + 1) + ".[" + list.get(i).getStatusIcon() + "] " + list.get(i).getDescription());
                }
                System.out.println("\t" + divider);
                continue;
            }

            // Input 'mark' Command
            if (command.equals("mark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                list.get(index).setMark();
                System.out.println("\t" + divider);
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t[X] " + list.get(index).getDescription());
                System.out.println("\t" + divider);
                continue;
            }

            // Input 'unmark' Command
            if (command.equals("unmark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                list.get(index).setUnmark();
                System.out.println("\t" + divider);
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t[ ] " + list.get(index).getDescription());
                System.out.println("\t" + divider);
                continue;
            }

            // Adds to list
            Task task = new Task(input);
            list.add(task);
            System.out.println("\t" + divider);
            System.out.println("\tadded: " + input);
            System.out.println("\t" + divider);
        }

        // Close Scanner
        System.out.println("\t" + divider);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t" + divider);
        scanner.close();
    }
}
