package duke;

import java.io.FileWriter;
import java.io.IOException;
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
    public static final String FILE_PATH = "src/main/duke.txt";



    public static void main(String[] args) {
        // print intro message
        printStartMessage();
        // setup bot
        setup();
        try {
            createFile();
            loadSavedTasksFile();
        } catch (FileNotFoundException exception){
            System.out.println("File not found.");
        }
        // run bot (decode task)
        run();
    }

    public static void createFile() {
        File savedTasksFile = new File(FILE_PATH);
        if (!savedTasksFile.exists()) {
            try {
                savedTasksFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
        String description = taskDescription.substring(0, taskDescription.indexOf("by") - 2);
        String by = taskDescription.substring(taskDescription.indexOf("by") + bySize);
        Deadline newDeadline = new Deadline(description, by, isDone);
        TASKS.add(newDeadline);
        printTaskAddedMessage(newDeadline, false);
    }

    public static void addTodo(String taskDescription, boolean isDone) {
        boolean exceptionPresent = true;
        try {
            if (taskDescription.length() == 0) {
                throw new DukeException();
            } else {
                Todo newTodo = new Todo(taskDescription, isDone);
                TASKS.add(newTodo);
                printTaskAddedMessage(newTodo, false);
            }
        } catch (DukeException exception) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void addEvent(String taskDescription, boolean isDone) {
        String[] dates =  taskDescription.split("/from | /to |from: | to: ");
        /*
        System.out.println("start");
        for (String s : dates) {
            System.out.println(s);
        }
        System.out.println("end");
        */
        String description = dates[0].substring(0, dates[0].length() - 1);
        String fromDate = dates[1];;
        String toDate = dates[2];
        Event newEvent = new Event(description, fromDate, toDate, isDone);
        TASKS.add(newEvent);
        printTaskAddedMessage(newEvent, false);
    }

    public static void printTaskAddedMessage(Task task, boolean excpetionPresent) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        if (!excpetionPresent) {
            tasksI++;
            System.out.println("Now you have " + tasksI + " tasks in the list.");
        }
    }

    public static void createTask(String taskType, String[] taskDescription, boolean isDone) {
        /*
        System.out.println("start");
        for (String s : taskDescription) {
            System.out.println(s);
        }
        System.out.println("end");
         */
        String description = String.join(" ", taskDescription).substring(taskType.length() + 1);
        // System.out.println("description: " + description);
        boolean exceptionPresent = false;
        if (taskType.equals(DEADLINE)) {
            addDeadline(description, isDone);
        } else if (taskType.equals(TODO)) {
            addTodo(description, isDone);
        } else if (taskType.equals(EVENT)) {
            addEvent(description, isDone);
        }
    }


    // src: https://www.w3schools.com/java/java_files_create.asp
    public static void saveTaskToFile() throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH);
        writer.write(formatFile());
        writer.close();
    }

    public static String formatFile () {
        String formattedList = "";
        for (int i = 1; i <= tasksI; i++) {
            Task task = TASKS.get(i);
            String taskType = task.getTaskType();
            String taskStatus = task.getStatusIcon();
            String taskDescription = task.getDescription();
            if (taskStatus.equals("X")) {
                taskStatus = "1";
            } else {
                taskStatus = "0";
            }
            if (taskType.equals("T")) {
                formattedList += taskType + " | " + taskStatus + " | " + taskDescription + System.lineSeparator();
            } else if (taskType.equals("D")){
                Deadline deadline = (Deadline) task;
                formattedList += taskType + " | " + taskStatus + " | " + taskDescription + " | " + deadline.getDeadline() + System.lineSeparator();
            } else {
                Event event = (Event) task;
                formattedList += taskType + " | " + taskStatus + " | " + taskDescription + " | from: " + event.getFromDate() + " to: " + event.getToDate() + System.lineSeparator();
            }
        }
        return formattedList;
    }

    public static void loadSavedTasksFile() throws FileNotFoundException {
        File savedTasksFile = new File(FILE_PATH);
        if (savedTasksFile.exists()) {
            Scanner scanner = new Scanner(savedTasksFile);
            while (scanner.hasNext()) {
                String task = scanner.nextLine();
                String[] splitTask = task.split(" \\| ");
                /*
                System.out.println("start");
                for (String s : splitTask) {
                    System.out.println(s);
                }
                System.out.println("end");
                 */
                addFileDataToList(splitTask);
            }
        }
    }

    public static void addFileDataToList(String[] input) {
        boolean isDone = Boolean.parseBoolean(input[1]);
        if (input[0].equals("T") || input[0].equals("D") || input[0].equals("E")) {
            if (input[0].equals("T")) {
                addTodo(input[2], isDone);
            } else if (input[0].equals("D")) {
                addDeadline(input[2] + " /by " + input[3], isDone);
            } else {
                addEvent( input[2] + " " + input[3], isDone);
            }
        }
    }


    public static void deleteTask(Task task) {
        TASKS.remove(task);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        tasksI--;
        System.out.println("Now you have " + tasksI + " tasks in the list.");
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
                    createTask(input[0], input, false);
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

            try {
                saveTaskToFile();
            } catch (IOException e) {
                System.out.println("I/O Error!");
            }

            input = scan.nextLine().split(" ");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }



}



