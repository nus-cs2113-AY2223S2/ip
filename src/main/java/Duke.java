import java.util.Scanner;
import java.util.ArrayList;
import commands.Event;
import commands.Task;
import commands.Deadline;
import commands.Todo;
import exceptions.DukeException;

public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String errorMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String todoError = "☹ OOPS!!! The description of a todo cannot be empty.";
    private static ArrayList<Task> list = new ArrayList<Task>();

    public static void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(LINE);
    }

    public static void bye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void printList() {
        System.out.println(LINE);
        int numTask = list.size();
        if (numTask == 0) {
            System.out.println("No task added yet");
            System.out.println(LINE);
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i+1) + "." + list.get(i).toString());
            }
            System.out.println(LINE);
        }
    }

    public static void markTask(int index) {
        System.out.println(LINE);
        list.get(index).markDone();
        System.out.println("Awesome! I've mark this task as done:");
        System.out.println("[" + list.get(index).getStatusIcon() + "] " + list.get(index).description);
    }

    public static void unmarkTask(int index) {
        System.out.println(LINE);
        list.get(index).markUndone();
        System.out.println("What!?!? OK, I've marked this task as not done yet:");
        System.out.println("[" + list.get(index).getStatusIcon() + "] " + list.get(index).description);
    }

    public static void deleteTask(int index) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + list.get(index).toString());
        list.remove(index);
        int size = list.size();
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public static void addTodo(String input) throws DukeException {
        try {
            Todo task = new Todo(input);
            list.add(task);
            if (task.description.split(" ").length < 2) {
                throw new DukeException(todoError);
            } else {
                System.out.println(LINE);
                System.out.println("Roger! The Todo task has been added: \n    " + task.toString());
                System.out.println("Now you have " + list.size() + " in the list");
                System.out.println(LINE);
            }
        } catch (DukeException e) {
            printError(e);
        }
    }

    public static void addDeadline(String input) {
        Deadline task = new Deadline(input);
        list.add(task);
        System.out.println(LINE);
        System.out.println("Roger! The Deadline task has been added: \n    " + task.toString());
        System.out.println("Now you have " + list.size() + " in the list");
        System.out.println(LINE);
    }

    public static void addEvent(String input) {
        Event task = new Event(input);
        list.add(task);
        System.out.println(LINE);
        System.out.println("Roger! The Deadline task has been added: \n    " + task.toString());
        System.out.println("Now you have " + list.size() + " in the list");
        System.out.println(LINE);
    }

    private static void runCommand(String input, String command) throws DukeException {
        if (command.equalsIgnoreCase("todo")) {
            addTodo(input);
        } else if (command.equalsIgnoreCase("deadline")) {
            addDeadline(input);
        } else if (command.equalsIgnoreCase("event")) {
            addEvent(input);
        } else if (command.equalsIgnoreCase("list")) {
            printList();
        } else if (command.equalsIgnoreCase("mark")) {
            int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;
            markTask(taskIdx);
        } else if (command.equalsIgnoreCase("unmark")) {
            int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;
            unmarkTask(taskIdx);
        }
        else if (command.equalsIgnoreCase("delete")) {
            int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;
            deleteTask(taskIdx);
        } else {
            throw new DukeException(errorMessage);
        }
    }

    private static void printError(DukeException e) {
        System.out.println(LINE + "\n" + e.getMessage() + LINE);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        greet();
        String input, command;
        int inputCounter = 0;
        while (true) {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            command = input.split(" ")[0];
            if (command.equalsIgnoreCase("bye")) {
                bye();
                break;
            }
            try {
                runCommand(input, command);
            } catch (DukeException e) {
                printError(e);
            }

        }
    }
}
