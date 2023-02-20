package duke;


import java.util.Scanner;

public class Ui {
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";
    public static final String GREET_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String MARK_MESSAGE = "Nice! I've marked this task as done:";
    public static final String UNMARK_MESSAGE = "OK, I've marked this task as not done yet:";
    public static final String LINE = "____________________________________________________________\n";
    public static final String LS = System.lineSeparator();

    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String readCommand() {
        String command = in.nextLine();
        return command;
    }

    public static void addSpecialTaskMessage(TaskList tasks) {
        int lastElement = tasks.getTaskCount() - 1;
        System.out.println(LINE + tasks.getTaskFromIndex(lastElement).addTaskMessage() + "Now you have " +
                (lastElement + 1) + " tasks in the list." + System.lineSeparator() + LINE);
    }

    public static void emptyCommandMessage(String task) {
        System.out.println(LINE + " ☹ OOPS!!! The description of a " + task + " cannot be empty.\n" + LINE);
    }

    public static void illegalCommandMessage() {
        System.out.println(LINE + " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE);
    }

    public static void printList(TaskList tasks) {
        System.out.print(LINE);
        for (int i = 0; i < tasks.getTaskCount(); ++i) {
            System.out.println((i + 1) + "." + tasks.getTaskFromIndex(i).toString());
        }
        System.out.println(LINE);
    }

    public static void greeting() {
        System.out.println(LINE + GREET_MESSAGE);
    }

    public static void deleteTaskMessage(int deleteIndex, TaskList tasks) {
        System.out.println(LINE + tasks.getTaskFromIndex(deleteIndex).deleteTaskMessage() + "Now you have "
                + (tasks.getTaskCount() - 1) + " tasks in the list." + System.lineSeparator() + LINE);
    }

    public static void updateDatabaseFailureMessage() {
        System.out.println("Update database failure");
    }

    public static void goodbyeMessage() {
        System.out.print(LINE + EXIT_MESSAGE + System.lineSeparator() + LINE);
    }

    public static void markChangeMessage(String command, int indexOfMarking, TaskList tasks) {
        System.out.print(LINE);
        if (command.startsWith("mark ")) {
            System.out.println(MARK_MESSAGE);
        } else {
            System.out.println(UNMARK_MESSAGE);
        }
        System.out.println("  " + tasks.getTaskFromIndex(indexOfMarking).toString() + LS + LINE);
    }
}
