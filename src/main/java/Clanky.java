import java.util.Scanner;

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

        // Scan for Input until Bye Command
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println("\t=============================================");
            System.out.println("\t" + input);
            System.out.println("\t=============================================");
        }

        // Close Scanner
        System.out.println("\t=============================================");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t=============================================");
        scanner.close();
    }
}
