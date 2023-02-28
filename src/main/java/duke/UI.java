package duke;

import duke.data.textImage;

public class UI {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void printTodo(String message) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [T][ ] " + message);
    }

    public static void printDeadline(String taskName, String by) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [D][ ] " + taskName + " (by: " + by + ")");
    }

    public static void printEvent(String taskName, String start, String end) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [E][ ] " + taskName + " (from: " + start + " to: " + end + ")");
    }

    public static void printTaskList(int taskListLength) {
        System.out.println("Now you have " + taskListLength + " tasks in the list.");
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void showWelcomeMessage() {
        System.out.println(textImage.welcomeTextImage);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}



