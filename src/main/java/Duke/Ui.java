package Duke;

import Duke.commands.Task;

import java.util.ArrayList;

public class Ui {
    protected static final String dividingLine = "\n————————————————————————————————————————————————————————\n";
    protected static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void printHello() {
        System.out.println(dividingLine);
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?" + dividingLine);
    }

    public static void printBye(){
        System.out.println(dividingLine + "Bye. Hope to see you again soon!" + dividingLine);
    }

    public static void printMark(String task) {
        System.out.println(dividingLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(dividingLine);
    }

    public static void printUnmark(String task) {
        System.out.println(dividingLine);
        System.out.println("Noted. I've unmarked this task:");
        System.out.println(task);
        System.out.println(dividingLine);
    }

    public static void printDelete(String task, int size) {
        System.out.println(dividingLine + System.lineSeparator() + "Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + (size - 1) + " tasks in the list." + System.lineSeparator() + dividingLine);
    }

    public static void printAddTask(String description, int size) {
        System.out.println(dividingLine+ System.lineSeparator() + "Got it. I've added this task:");
        System.out.println(description);
        System.out.println("Now you have " + size + " tasks in the list." + System.lineSeparator() + dividingLine);
    }


    public static void listTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println(dividingLine);
            System.out.println("No task found");
            System.out.println(dividingLine);
            return;
        }
        System.out.println(dividingLine + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + (tasks.get(i)).toString());
        }
        System.out.println("Now you have " + tasks.size() + " tasks in the list." + dividingLine);
    }

    public static void printUnrecognisableCommand(){
        System.out.println(dividingLine + "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                + dividingLine);
    }
}
