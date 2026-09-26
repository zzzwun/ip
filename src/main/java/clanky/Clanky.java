package clanky;

import clanky.task.Deadline;
import clanky.task.Event;
import clanky.task.Task;
import clanky.task.Todo;
import clanky.task.TaskList;

import java.io.File;
import java.util.Scanner;

public class Clanky {
    public static void main(String[] args){

        // Instantiate UI to handle printing
        UI ui = new UI();
        ui.showWelcome();

        // Load from Storage & Populate List
        Storage storage = new Storage(
                System.getProperty("user.home") + File.separator + ".clanky" + File.separator + "clanky.txt"
        );

        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (ClankyException e) {
            ui.showLoadError(e.getMessage());
            tasks = new TaskList();
        }

        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);
        String input = "";

        // Scan for Input
        label:
        while (true) {
            // Gets Input
            input = scanner.nextLine();

            // Checks empty input
            if (input.trim().isEmpty()) {
                continue;
            }

            // Splits Up Input Into Command & Arguments
            String[] parts = Parser.parse(input);
            String command = parts[0];
            String argument = parts.length > 1 ? parts[1] : null;

            try {
                switch (command) {
                    // Input 'bye' Command
                    case "bye":
                        break label;

                    // Input 'list' Command
                    case "list": {
                        ui.showList(tasks);
                        break;
                    }

                    // Input 'mark' Command
                    case "mark": {
                        int index = Parser.parseIndex(parts, tasks, "mark");
                        Task task = tasks.get(index);
                        task.setMark();
                        ui.showTaskMarked(task);
                        break;
                    }

                    // Input 'unmark' Command
                    case "unmark": {
                        int index = Parser.parseIndex(parts, tasks, "unmark");
                        Task task = tasks.get(index);
                        task.setUnmark();
                        ui.showTaskUnmarked(task);
                        break;
                    }

                    // Input 'to-do' command
                    case "todo": {
                        String desc = Parser.parseTodoArgs(argument);
                        Task task = new Todo(desc);
                        tasks.add(task);
                        ui.showTaskAdded(task, tasks.size());
                        break;
                    }

                    // Input 'deadline' command
                    case "deadline": {
                        String[] deadlineArgs = Parser.parseDeadlineArgs(argument);
                        Task task = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                        tasks.add(task);
                        ui.showTaskAdded(task, tasks.size());
                        break;
                    }

                    // Input 'event' command
                    case "event": {
                        String[] eventArgs = Parser.parseEventArgs(argument);
                        Task task = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
                        tasks.add(task);
                        ui.showTaskAdded(task, tasks.size());
                        break;
                    }

                    // Input 'delete' command
                    case "delete": {
                        int index = Parser.parseIndex(parts, tasks, "delete");
                        Task removed = tasks.remove(index);
                        ui.showTaskDeleted(removed, tasks.size());
                        break;
                    }

                    // Default case
                    default: {
                        throw new ClankyException("Idk what's that command.");
                    }

                }

                // Saves to Storage
                storage.save(tasks.asArrayList());

            } catch (ClankyException e){
                ui.showError(e.getMessage());
            }

        }

        // Close Scanner
        ui.showGoodbye();
        scanner.close();
    }

}
