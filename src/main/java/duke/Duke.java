package duke;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {

    static final Task[] TASKS = new Task[101];
    static int tasksI = 1;

    public static void main(String[] args) {
        // print intro message
        start();
        loadTaskData();
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

    public static void deadline(String taskDescript, boolean isTaskDone) {
        int bySize = 3;
        String by = taskDescript.substring(taskDescript.indexOf("by") + bySize);
        Deadline deadline = new Deadline(taskDescript, by, isTaskDone);
        TASKS[tasksI] = deadline;
        System.out.println("Got it. I've added this task: ");
        System.out.println(deadline.toString());
    }

    public static boolean todo(String taskDescript, boolean isTaskDone) {
        boolean exceptionPresent = true;
        try {
            if (taskDescript.length() == 0) {
                throw new DukeException();
            } else {
                Todo todo = new Todo(taskDescript, isTaskDone);
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

    public static void event(String taskDescript, String date, boolean isTaskDone) {
        Event event = new Event(taskDescript, date, isTaskDone);
        TASKS[tasksI] = event;
        System.out.println("Got it. I've added this task:");
        System.out.println(event.toString());
    }

    public static void task(String taskType, String[] taskDescript) {
        String descript = String.join(" ", taskDescript).substring(taskType.length());
        boolean exceptionPresent = false;
        if (taskType.equals("deadline")) {
            deadline(descript, false);
        } else if (taskType.equals("todo")) {
            exceptionPresent = todo(descript, false);
        } else if (taskType.equals("event")) {
            String[] dates =  descript.split("/from | /to ");
            String fromDate = dates[1];
            String toDate = dates[2];
            event(descript, fromDate + "-" + toDate, false);
        }
        if (!exceptionPresent) {
            System.out.println("Now you have " + tasksI + " tasks in the list.");
            tasksI++;
        }
    }

    public static void loadTaskData() {
        try {
            File tasks = new File("saved/duke.txt");
            Scanner tasksList = new Scanner(tasks);
            while (tasksList.hasNext()) {
                String[] currentTask = tasksList.nextLine().split(";");
                String taskType = currentTask[0];
                boolean isTaskDone = false;
                if (currentTask[1].equals(" 1 ")) {
                    isTaskDone = true;
                }
                String taskDescription = currentTask[2];
                if (taskType.equals("t ")) {
                    Todo newTodo = new Todo(taskDescription, isTaskDone);
                    TASKS[tasksI] = newTodo;
                } else if (taskType.equals("d ") || taskType.equals("e ")) {
                    String date = currentTask[3].substring(1);
                    if (taskType.equals("d" )) {
                        Deadline newDeadline = new Deadline(taskDescription, date, isTaskDone);
                        TASKS[tasksI] = newDeadline;
                    } else {
                        Event newEvent = new Event(taskDescription, date, isTaskDone);
                        TASKS[tasksI] = newEvent;
                    }
                }
                tasksI++;
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File not found!");
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



