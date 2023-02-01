import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String LINE = "____________________________________________________________";
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
        }
        else {
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

    public static void addTodo(String input) {
        Todo task = new Todo(input);
        list.add(task);
        System.out.println(LINE);
        System.out.println("Roger! The Todo task has been added: \n    " + task.toString());
        System.out.println("Now you have " + list.size() + " in the list");
        System.out.println(LINE);
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
            else if (command.equalsIgnoreCase("todo")) {
                addTodo(input);
            }
            else if (command.equalsIgnoreCase("deadline")) {
                addDeadline(input);
            }
            else if (command.equalsIgnoreCase("event")) {
                addEvent(input);
            }
            else if (command.equalsIgnoreCase("list")) {
                printList();
            }
            else if (command.equalsIgnoreCase("mark")) {
                int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;
                markTask(taskIdx);
            }
            else if (command.equalsIgnoreCase("unmark")) {
                int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;
                unmarkTask(taskIdx);
            }
        }
    }
}
