package duke;

<<<<<<< HEAD
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
=======
import java.lang.reflect.Array;
>>>>>>> branch-Level-6
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import dukeException.DukeException;
import dukeException.DukeIOBException;

public class Duke {

    public Duke() {

    }

    /*
    Main function that takes user input and interpets how to store and what to do with it
     */
    public static void main(String[] args) {

        ArrayList<String> userInputs = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        try {
            Files.createDirectories(Path.of("./data"));

            File myObj = new File("./data/duke.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                readDukeText();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        greetUser();

        while (true) {
            String input = scan.nextLine();
            String[] splitInput = input.split(" ");
<<<<<<< HEAD
            int taskSize = tasks.size();
=======
>>>>>>> branch-Level-6
            switch (splitInput[0]) {
            case "bye":
                exit();
                return;
            case "todo":
<<<<<<< HEAD
                insertTodo(input, false);
                break;
            case "event":
                insertEvent(input, false);
                break;
            case "deadline":
                insertDeadline(input, false);
=======
                insertTodo(input);
                break;
            case "event":
                insertEvent(input);
                break;
            case "deadline":
                insertDeadline(input);
>>>>>>> branch-Level-6
                break;
            case "list":
                listOut(userInputs, tasks);
                break;
            case "mark":
                markTask(splitInput);
                break;
            case "unmark":
                unMarkTask(splitInput);
                break;
            case "delete":
                deleteTask(splitInput);
                break;
            default:
                System.out.println("\t____________________________________________________________");
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");;
                System.out.println("\t____________________________________________________________");
                break;
            }
<<<<<<< HEAD
            if (taskSize != tasks.size()) {
                saveTasks();
            }

        }
    }
    public static void saveTasks() {
        try {
            FileWriter myWriter = new FileWriter("./data/duke.txt");
            myWriter.flush();
            for (Task task : tasks) {
                myWriter.write(task.toString() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void readDukeText() {

        try {
            ArrayList<String> tmpStrTasks = new ArrayList<>();
            tmpStrTasks = (ArrayList<String>) Files.readAllLines(Paths.get("./data/duke.txt"), StandardCharsets.UTF_8);
            for (int i = 0; i < tmpStrTasks.size(); i++) {
                System.out.println(tmpStrTasks.get(i).substring(7));
            }
            for (int i = 0; i < tmpStrTasks.size(); i++) {
                String newTask = tmpStrTasks.get(i);
                Task tsk = null;
                boolean isMark = newTask.charAt(4) == 'X';
                switch (newTask.charAt(1)) {
                case 'T':
                    insertTodo("todo " + newTask.substring(7), isMark);
                    break;
                case 'D':
                    insertDeadline("deadline " + newTask.substring(7), isMark);
                    break;
                case 'E':
                    insertEvent("event " + newTask.substring(7), isMark);
                    break;
                }
                System.out.println(newTask);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

=======

        }
    }
>>>>>>> branch-Level-6
    public static void deleteTask(String[] splitInput) {
        String tmpTask = tasks.get(Integer.parseInt(splitInput[1]) - 1).toString();
        tasks.remove(tasks.get(Integer.parseInt(splitInput[1]) - 1));
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + tmpTask);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }
    public static void unMarkTask(String[] splitInput) {
        tasks.get(Integer.parseInt(splitInput[1]) - 1).unMark();
        System.out.println("\t____________________________________________________________");
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + tasks.get(Integer.parseInt(splitInput[1]) - 1));
        System.out.println("\t____________________________________________________________");
    }
    public static void markTask(String[] splitInput) {
        tasks.get(Integer.parseInt(splitInput[1]) - 1).mark();
        System.out.println("\t____________________________________________________________");
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + tasks.get(Integer.parseInt(splitInput[1]) - 1));
        System.out.println("\t____________________________________________________________");
    }
<<<<<<< HEAD
    public static void insertTodo(String input, boolean isMark) {

        try {
            Task tsk = new Todo(input.substring(5), isMark);
=======
    public static void insertTodo(String input) {
        try {
            Task tsk = new Todo(input.substring(5), false);
>>>>>>> branch-Level-6
            tasks.add(tsk);
            addTaskPrint(tasks, tsk);
        } catch (IndexOutOfBoundsException de) {
            printExceptionMsg("todo", "description of a todo cannot be empty.");
        } catch (DukeException de) {

        }
    }
    public static void printExceptionMsg(String task, String err) {
        System.out.println("\t____________________________________________________________");
        System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
        System.out.println("\t____________________________________________________________");
    }
    public static void addTaskPrint(ArrayList<Task> tasks, Task tsk) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + tsk.toString());
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }
    /*
    This Returns the input as a Deadline object
     */
<<<<<<< HEAD
    public static void insertDeadline(String input, boolean isMark) {
=======
    public static void insertDeadline(String input) {
>>>>>>> branch-Level-6
        int idx = input.indexOf("/by");
        String desc = input.substring(8, idx);
        String by = input.substring(idx + 3);
        Deadline tsk = null;
        try {
            tsk = new Deadline(desc, false, by);
            addTaskPrint(tasks, tsk);
        } catch (DukeException de) {

        }
        tasks.add(tsk);
    }
    /*
    This Returns the input as a Event object
     */
<<<<<<< HEAD
    public static void insertEvent(String input, boolean isMark) {
=======
    public static void insertEvent(String input) {
>>>>>>> branch-Level-6
        int idx = input.indexOf("/from");
        int idx1 = input.indexOf("/to");
        String desc = input.substring(5, idx);
        String start = input.substring(idx + 5, idx1);
        String end = input.substring(idx1 + 3);
        Event tsk = null;
        try {
            tsk = new Event(desc, false, start, end);
            addTaskPrint(tasks, tsk);
        } catch (DukeException de) {

        }
        tasks.add(tsk);
    }
    /*
    This Adds the input to an input array for the ability to keep track of
     */
    public static void addToList(String cmd, ArrayList<String> userInputs) {
        userInputs.add(cmd);
        userInputs.set(userInputs.size() - 1, userInputs.size() + ". [ ] " + userInputs.get(userInputs.size() - 1));
    }
    /*
    This method lists out the tasks in order
    */
    public static void listOut(ArrayList<String> userInputs, ArrayList<Task> tasks) {
        System.out.println("\t____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("\t____________________________________________________________");
    }
    /*
    Automated greet function
    */
    public static void greetUser() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }
    /*
    Exit message
    */
    public static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");

    }

}