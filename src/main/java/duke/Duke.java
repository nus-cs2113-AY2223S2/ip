package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static int totalTasks = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    private static void printDuke() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");
    }

    private static String breakLine() {
        return "\t--------------------------------------------------------------\n";
    }

    // Greet
    private static void greet() {
        System.out.println(breakLine()
                + "\tHello! I'm Duke :)\n"
                + "\tWhat can I do for you?\n"
                + breakLine());
    }

    // add a new task
    private static void addTodo(String taskInfo) {
        Task newTask = new Todo(taskInfo);
        tasks.add(newTask);
        System.out.print(breakLine()
                + "\tadded:\n\t\t" + newTask + '\n'
                + "\t(total: " + (totalTasks + 1) + " tasks)\n"
                + breakLine());
    }

    // create deadline
    private static Deadline createDeadline(String taskInfo) {
        String description, deadline;
        String[] info = taskInfo.split("#by", 2);
        description = info[0];
        deadline = info[1];
        return new Deadline(description, deadline);
    }

    // add a new deadline
    private static void addDeadline(String taskInfo) {
        Task newTask = createDeadline(taskInfo);
        tasks.add(newTask);
        System.out.print(breakLine()
                + "\tadded:\n\t\t" + newTask + '\n'
                + "\t(total: " + (totalTasks + 1) + " tasks)\n"
                + breakLine());
    }

    // create new event
    private static Event createEvent(String taskInfo) {
        String description, from, to;
        String[] info = taskInfo.split("#from", 2);
        description = info[0];
        info = info[1].split("#to", 2);
        from = info[0];
        to = info[1];
        return new Event(description, from, to);
    }

    // add a new event
    private static void addEvent(String taskInfo) {
        Task newTask = createEvent(taskInfo);
        tasks.add(newTask);
        System.out.print(breakLine()
                + "\tadded:\n\t\t" + newTask + '\n'
                + "\t(total: " + (totalTasks + 1) + " tasks)\n"
                + breakLine());
    }

    // list all tasks
    private static void listTask() {
        System.out.println(breakLine() + "\tThese are the tasks you have (" + tasks.size() + " tasks):");
        int order = 1;
        for (Task task : tasks) {
            System.out.print("\t" + order + ". " + task.toString() + '\n');
            order++;
        }
        System.out.print(breakLine());
    }

    // mark the task
    private static void markTask(String taskNumber) {
        int taskIdx = Integer.parseInt(taskNumber) - 1;
        try {
            Task currentTask = tasks.get(taskIdx);
            currentTask.mark();
            System.out.print(breakLine()
                    + "\tNice! I've marked this task as done :D\n\t\t"
                    + currentTask + '\n'
                    + breakLine());
        } catch (IndexOutOfBoundsException e) {
            System.out.print(breakLine()
                    + "\t(!) Invalid task number. Please try again :(\n"
                    + breakLine());
        }
    }

    // unmark the task
    private static void unmarkTask(String taskNumber) {
        int taskIdx = Integer.parseInt(taskNumber) - 1;
        try {
            Task currentTask = tasks.get(taskIdx);
            currentTask.mark();
            System.out.print(breakLine()
                    + "\tOh. I've unmarked this task as not done yet :(\n\t"
                    + currentTask + '\n'
                    + breakLine());
        } catch (IndexOutOfBoundsException e) {
            System.out.print(breakLine()
                    + "\t(!) Invalid task number. Please try again :(\n"
                    + breakLine());
        }
    }

    // delete Task
    private static void deleteTask(String taskNumber) {
        int taskIdx = Integer.parseInt(taskNumber) - 1;
        try {
            Task currentTask = tasks.get(taskIdx);
            tasks.remove(taskIdx);
            System.out.println(breakLine()
                    + "\tOk! I've deleted the task :D\n\t\t"
                    + currentTask);
            System.out.print("\tThere are " + tasks.size() + " remaining tasks.\n"
                    + breakLine());
        } catch (IndexOutOfBoundsException e) {
            System.out.print(breakLine()
                    + "\t(!) Invalid task number. Please try again :(\n"
                    + breakLine());
        }
    }

    // read input
    private static String readInput() {
        System.out.print(">> ");
        return in.nextLine();
    }

    // exit
    private static void exit() {
        System.out.print(breakLine()
                + "\tBa-bye. Hope to see you again soon :)\n"
                + breakLine());
    }

    public static void main(String[] args) {
        String command, input;
        String[] inputArgs;

        printDuke();
        greet();

        while (true) {
            input = readInput();
            inputArgs = input.split(" ", 2);
            command = inputArgs[0];

            try {
                if (command.equals("bye")) {
                    break;
                } else if (command.equals("list")) {
                    listTask();
                } else if (command.equals("mark")) {
                    markTask(inputArgs[1]);
                } else if (command.equals("unmark")) {
                    unmarkTask(inputArgs[1]);
                } else if (command.equals("todo")) {
                    addTodo(inputArgs[1]);
                } else if (command.equals("deadline")) {
                    addDeadline(inputArgs[1]);
                } else if (command.equals("event")) {
                    addEvent(inputArgs[1]);
                } else if (command.equals("delete")) {
                    deleteTask(inputArgs[1]);
                } else {
                    System.out.print(breakLine()
                            + "\t(!) Invalid command :(\n"
                            + breakLine());
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.print(breakLine()
                        + "\t(!) Please provide the appropriate information for the task\n"
                        + breakLine());
            }
        }
        exit();
    }
}
