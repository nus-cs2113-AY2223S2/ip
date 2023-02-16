package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {

    public static final ArrayList<Task> TASKS = new ArrayList<Task>();
    public static int tasksI = 0;
    public static final String DEADLINE = "deadline";
    public static final String TODO = "todo";
    public static final String EVENT = "event";
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "mark";
    public static final String DELETE = "delete";



    public static void main(String[] args) {
        // print intro message
        printStartMessage();
        // setup bot
        setup();
        loadTaskData();
        // run bot (decode task)
        run();
    }

    public static void printStartMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void setup() {
        Task fillerTask = new Task("filler", false);
        TASKS.add(fillerTask);
    }

    public static void listTasks() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= tasksI; i++) {
            Task currTask = TASKS.get(i);
            System.out.println(i + ". " + currTask.toString());
        }
    }

    public static void markTask(Task task) {
        task.mark();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.toString());
    }

    public static void unmarkTask(Task task) {
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(task.toString());
    }

    public static void addDeadline(String taskDescription, boolean isDone) {
        int bySize = 3;
        String by = taskDescription.substring(taskDescription.indexOf("by") + bySize);
        Deadline newDeadline = new Deadline(taskDescription, by, isDone);
        TASKS.add(newDeadline);
        printTaskAddedMessage(newDeadline);
    }

    public static boolean addTodo(String taskDescription, boolean isDone) {
        boolean exceptionPresent = true;
        try {
            if (taskDescription.length() == 0) {
                throw new DukeException();
            } else {
                Todo newTodo = new Todo(taskDescription, isDone);
                TASKS.add(newTodo);
                printTaskAddedMessage(newTodo);
                return !exceptionPresent;
            }
        } catch (DukeException exception) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            return exceptionPresent;
        }
    }

    public static void addEvent(String taskDescription, String date, boolean isDone) {
        Event newEvent = new Event(taskDescription, date, isDone);
        TASKS.add(newEvent);
        printTaskAddedMessage(newEvent);
    }

    public static void printTaskAddedMessage(Task task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
    }

    public static void createTask(String taskType, String[] taskDescription) {
        String description = String.join(" ", taskDescription).substring(taskType.length());
        boolean exceptionPresent = false;
        if (taskType.equals(DEADLINE)) {
            addDeadline(description, false);
        } else if (taskType.equals(TODO)) {
            exceptionPresent = addTodo(description, false);
        } else if (taskType.equals(EVENT)) {
            String[] dates =  description.split("/from | /to ");
            String fromDate = dates[1];
            String toDate = dates[2];
            addEvent(description, fromDate + "-" + toDate, false);
        }
        if (!exceptionPresent) {
            tasksI++;
            System.out.println("Now you have " + tasksI + " tasks in the list.");
        }
    }

    public static void deleteTask(Task task) {
        TASKS.remove(task);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        tasksI--;
        System.out.println("Now you have " + tasksI + " tasks in the list.");
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
                    TASKS.add(newTodo);
                } else if (taskType.equals("d ") || taskType.equals("e ")) {
                    String date = currentTask[3].substring(1);
                    if (taskType.equals("d" )) {
                        Deadline newDeadline = new Deadline(taskDescription, date, isTaskDone);
                        TASKS.add(newDeadline);
                    } else {
                        Event newEvent = new Event(taskDescription, date, isTaskDone);
                        TASKS.add(newEvent);
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
        while (!input[0].equals(BYE)) {
            try{
                // want to see all the tasks in a list
                if (input[0].equals(LIST)) {
                    listTasks();
                // mark a task
                } else if (input[0].equals(MARK)) {
                    Task taskToMark = TASKS.get(Integer.parseInt(input[1]));
                    markTask(taskToMark);
                // unmark a task
                } else if (input[0].equals(UNMARK)) {
                    Task taskToUnmark = TASKS.get(Integer.parseInt(input[1]));
                    unmarkTask(taskToUnmark);
                // a task
                } else if (input[0].equals(DEADLINE) || input[0].equals(TODO) || input[0].equals(EVENT)) {
                    createTask(input[0], input);
                // delete a task
                } else if (input[0].equals(DELETE)) {
                    Task taskToDelete = TASKS.get(Integer.parseInt(input[1]));
                    deleteTask(taskToDelete);
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



