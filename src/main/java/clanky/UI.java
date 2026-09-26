package clanky;

import clanky.task.Task;

import java.util.ArrayList;

public class UI {

    private static final String BANNER = "  ____   _          _      _   _   _  __ __   __\n"
            + " / ___| | |        / \\    | \\ | | | |/ / \\ \\ / /\n"
            + "| |     | |       / _ \\   |  \\| | | ' /   \\ V / \n"
            + "| |___  | |___   / ___ \\  | |\\  | | . \\    | |  \n"
            + " \\____| |_____| /_/   \\_\\ |_| \\_| |_|\\_\\   |_|";

    private static final String DIVIDER = "=================================================";

    public void showWelcome() {
        System.out.println(BANNER);
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm Clanky.");
        System.out.println("What can I do for you ?");
        System.out.println(DIVIDER);
    }

    public void showGoodbye() {
        printBlock("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        printBlock(message);
    }

    public void showLoadError(String message) {
        System.out.println("\t" + DIVIDER);
        System.out.println("\tCouldn't load saved tasks: " + message);
        System.out.println("\tStarting with an empty list.");
        System.out.println("\t" + DIVIDER);
    }

    public void showList(ArrayList<Task> list) {
        System.out.println("\t" + DIVIDER);
        if (list.isEmpty()) {
            System.out.println("\tNo Tasks Yet");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + list.get(i));
        }
        System.out.println("\t" + DIVIDER);
    }

    public void showTaskAdded(Task task, int listSize) {
        System.out.println("\t" + DIVIDER);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + listSize + " task" + (listSize == 1 ? "" : "s") + " in the list.");
        System.out.println("\t" + DIVIDER);
    }

    public void showTaskDeleted(Task task, int listSize) {
        System.out.println("\t" + DIVIDER);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + listSize + " task" + (listSize == 1 ? "" : "s") + " in the list.");
        System.out.println("\t" + DIVIDER);
    }

    public void showTaskMarked(Task task) {
        System.out.println("\t" + DIVIDER);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + task);
        System.out.println("\t" + DIVIDER);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("\t" + DIVIDER);
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + task);
        System.out.println("\t" + DIVIDER);
    }

    private void printBlock(String message) {
        System.out.println("\t" + DIVIDER);
        System.out.println("\t" + message);
        System.out.println("\t" + DIVIDER);
    }

}
