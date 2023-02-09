package duke;

import java.util.Scanner;

public class Duke {

    static final Task[] TASKS = new Task[101];
    static int tasksI = 1;

    public static void main(String[] args) {
        // print intro message
        start();
        // run bot (decode task)
        run();
    }

    public static void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void list() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i < tasksI; i++) {
            Task currTask = TASKS[i];
            System.out.println(i + ". " + currTask.toString());
        }
    }

    public static void mark(String word, Task task) {
        task.mark();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.toString());
    }

    public static void unmark(String word, Task task) {
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(task.toString());
    }

    public static void deadline(String taskDescript) {
        int bySize = 3;
        String by = taskDescript.substring(taskDescript.indexOf("by") + bySize);
        Deadline deadline = new Deadline(taskDescript, by);
        TASKS[tasksI] = deadline;
        System.out.println("Got it. I've added this task: ");
        System.out.println(deadline.toString());
    }

    public static boolean todo(String taskDescript) {
        boolean exceptionPresent = true;
        try {
            if (taskDescript.length() == 0) {
                throw new DukeException();
            } else {
                Todo todo = new Todo(taskDescript);
                TASKS[tasksI] = todo;
                System.out.println("Got it. I've added this task: ");
                System.out.println(todo.toString());
                return !exceptionPresent;
            }
        } catch (DukeException exception) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            return exceptionPresent;
        }
    }

    public static void event(String taskDescript) {
        Event event = new Event(taskDescript);
        TASKS[tasksI] = event;
        System.out.println("Got it. I've added this task:");
        System.out.println(event.toString());
    }

    public static void task(String taskType, String[] taskDescript) {
        String descript = String.join(" ", taskDescript).substring(taskType.length());
        boolean exceptionPresent = false;
        if (taskType.equals("deadline")) {
            deadline(descript);
        } else if (taskType.equals("todo")) {
            exceptionPresent = todo(descript);
        } else if (taskType.equals("event")) {
            event(descript);
        }
        if (!exceptionPresent) {
            System.out.println("Now you have " + tasksI + " tasks in the list.");
            tasksI++;
        }
    }

    public static void run() {
        Scanner scan = new Scanner(System.in);
        String[] input = scan.nextLine().split(" ");
        while (!input[0].equals("bye")) {
            try{
                // want to see all the tasks in a list
                if (input[0].equals("list")) {
                    list();
                // mark a task
                } else if (input[0].equals("mark")) {
                    Task taskNum = TASKS[Integer.parseInt(input[1])];
                    mark(input[0], taskNum);
                // unmark a task
                } else if (input[0].equals("unmark")) {
                    Task taskNum = TASKS[Integer.parseInt(input[1])];
                    unmark(input[0], taskNum);
                // a task
                } else if (input[0].equals("deadline") || input[0].equals("todo") || input[0].equals("event")) {
                    task(input[0], input);
                } else {
                    throw new DukeException();
                }
            } catch (DukeException exception) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            input = scan.nextLine().split(" ");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}



