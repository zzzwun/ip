import java.util.Scanner;
import java.util.ArrayList;

public class Clanky {
    public static void main(String[] args) {
        // Initial Print Statments
        String banner = "  ____   _          _      _   _   _  __ __   __\n"
                + " / ___| | |        / \\    | \\ | | | |/ / \\ \\ / /\n"
                + "| |     | |       / _ \\   |  \\| | | ' /   \\ V / \n"
                + "| |___  | |___   / ___ \\  | |\\  | | . \\    | |  \n"
                + " \\____| |_____| /_/   \\_\\ |_| \\_| |_|\\_\\   |_|";

        System.out.println(banner);
        System.out.println("=================================================");
        System.out.println("Hello! I'm Clanky.");
        System.out.println("What can I do for you ?");
        System.out.println("=================================================");

        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);
        String input = "";

        // Initialize ArrayList
        ArrayList<String> list = new ArrayList<>();

        // Scan for Input
        while (true) {
            input = scanner.nextLine();

            // Input 'bye' Command
            if (input.equals("bye")) {
                break;
            }

            // Input 'list' Command
            if (input.equals("list")) {
                System.out.println("\t=============================================");
                if (list.isEmpty()) {
                    System.out.println("\tNo Tasks Yet");
                }
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + list.get(i));
                }
                System.out.println("\t=============================================");
                continue;
            }

            // Adds to list
            list.add(input);
            System.out.println("\t=============================================");
            System.out.println("\tadded: " + input);
            System.out.println("\t=============================================");
        }

        // Close Scanner
        System.out.println("\t=============================================");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t=============================================");
        scanner.close();
    }
}
