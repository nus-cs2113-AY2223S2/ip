import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.Todo;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws FileNotFoundException {

        printGreetingMessage();

        //Read in input from user
        String inputString;

        //Set up list to store user inputs
        ArrayList<Todo> tasks = new ArrayList<>();
        int counter = 0;


        //setup of exit flag
        boolean exit = false;

        //Initialize list with saved data
        counter = initializeList(tasks, counter);


        while (!exit) {
            String taskType;
            String task = null;

            inputString = getInputString();

            //Parsing user input
            int descriptionPosition = inputString.indexOf(" ");
            if(descriptionPosition == -1) {
                taskType = inputString;
            } else {
                int endPosition = inputString.length();
                taskType = inputString.substring(0, descriptionPosition);
                task = inputString.substring(descriptionPosition + 1, endPosition);
            }

            //switch cases for all specified input types
            switch (taskType) {
            case "bye":
                exit = true;
                break;

            case "list":
                System.out.println("    _________________________________________");
                printListContents(tasks, tasks.size());
                break;

            case "mark":
                int taskNumber = getTaskNumber(task);
                markAsDone(tasks, taskNumber);
                printMarkedAcknowledgement(tasks, taskNumber);
                break;

            case "unmark":
                taskNumber = getTaskNumber(task);
                tasks.get(taskNumber - 1).setDone(false);
                printUnmarkedAcknowledgement(tasks, taskNumber);
                break;

            case "todo":
                tasks.add(new Todo(task));
                tasks.get(counter).print();
                counter++;
                System.out.println("    Now you have " + counter + " tasks in your list!");
                break;

            case "deadline":
                int deadlinePosition = task.indexOf("/");
                int endOfLine = task.length();
                String taskName = task.substring(0, deadlinePosition);
                String deadline = task.substring(deadlinePosition + 1, endOfLine);
                tasks.add(new Deadline(taskName, deadline));
                tasks.get(counter).print();
                counter++;
                break;

            case "event":
                int deadlineStartPosition = task.indexOf("/");
                int deadlineEndPosition = task.indexOf("|");
                endOfLine = task.length();
                taskName = task.substring(0, deadlineStartPosition);
                String deadlineStart = task.substring(deadlineStartPosition + 1, deadlineEndPosition);
                String deadlineEnd = task.substring(deadlineEndPosition + 1, endOfLine);
                tasks.add(new Event(taskName, deadlineStart, deadlineEnd));
                tasks.get(counter).print();
                counter++;
                break;

            case "delete":
                taskNumber = getTaskNumber(task) - 1;
                System.out.println("    _________________________________________");
                tasks.get(taskNumber).printInList();
                System.out.println("    _________________________________________");
                System.out.println("    ");
                tasks.remove(tasks.get(taskNumber));
                counter--;
                break;

            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
    }

        //When user types "bye"
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _________________________________________");
        System.out.println("     ");

        //Save list data into text file on disk
        PrintWriter fw = new PrintWriter("out\\list.txt");
        for (int i = 0; i < counter; i++) {
            String classType = String.valueOf(tasks.get(i).getClass());
            if(classType.equalsIgnoreCase("Class Duke.Todo")) {
                fw.println("todo " + tasks.get(i).getDescription());
            } else if (classType.equalsIgnoreCase("Class Duke.Event")) {
                fw.println("event " + tasks.get(i).getDescription() + "/" + tasks.get(i).getBy() + "|" + tasks.get(i).getEnd());
            } else if (classType.equalsIgnoreCase("Class Duke.Deadline")) {
                fw.println("deadline " + tasks.get(i).getDescription() + "/" + tasks.get(i).getBy());
            }
        }
        fw.close();
    }

    /**
     * Marks a task as done.
     *
     * @param tasks list of tasks already added.
     * @param taskNumber number assigned to task in the list.
     */
    private static void markAsDone(ArrayList<Todo> tasks, int taskNumber) {
        tasks.get(taskNumber - 1).setDone(true);
    }


    /**
     * Prints greeting message when application is launched.
     */
    private static void printGreetingMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("    Hello from\n" + logo);
        System.out.println("    _________________________________________");

        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    _________________________________________");
        System.out.println("     ");
    }
    
    private static int initializeList(ArrayList<Todo> tasks, int counter) throws FileNotFoundException {
        String inputString;
        String task = null;
        String taskType;
        Scanner scanner = new Scanner(new File("out\\list.txt"));
        Scanner in = new Scanner(System.in);


        while (scanner.hasNextLine()) {
            inputString = scanner.nextLine();
            int descriptionPosition = inputString.indexOf(" ");
            if(descriptionPosition == -1) {
                taskType = inputString;
            } else {
                int endPosition = inputString.length();
                taskType = inputString.substring(0, descriptionPosition);
                task = inputString.substring(descriptionPosition + 1, endPosition);
            }

            switch (taskType) {
            case "todo":
                tasks.add(new Todo(task));
                tasks.get(counter).print();
                counter++;
                break;

            case "deadline":
                int deadlinePosition = task.indexOf("/");
                int endOfLine = task.length();
                String taskName = task.substring(0, deadlinePosition);
                String deadline = task.substring(deadlinePosition + 1, endOfLine);
                tasks.add(new Deadline(taskName, deadline));
                tasks.get(counter).print();
                counter++;
                break;

            case "event":
                int deadlineStartPosition = task.indexOf("/");
                int deadlineEndPosition = task.indexOf("|");
                endOfLine = task.length();
                taskName = task.substring(0, deadlineStartPosition);
                String deadlineStart = task.substring(deadlineStartPosition + 1, deadlineEndPosition);
                String deadlineEnd = task.substring(deadlineEndPosition + 1, endOfLine);
                tasks.add(new Event(taskName, deadlineStart, deadlineEnd));
                tasks.get(counter).print();
                counter++;
                break;
            }

        }

            scanner.close();
        return counter;
    }

    /**
     * Returns user input as a string.
     *
     * @return input string.
     */
    private static String getInputString() {
        String inputString;
        Scanner in;
        in = new Scanner(System.in);
        inputString = in.nextLine();
        return inputString;
    }

    /**
     * Prints acknowledgement message when task is marked as not done.
     *
     * @param tasks list of tasks already added.
     * @param taskNumber number assigned to task in the list.
     */
    private static void printUnmarkedAcknowledgement(ArrayList<Todo> tasks, int taskNumber) {
        System.out.println("    _________________________________________");
        System.out.println("    " + taskNumber + "." + "[ ] " + tasks.get(taskNumber - 1).getDescription());
        System.out.println("    _________________________________________");
    }

    /**
     * Prints acknowledgement message when task is marked as done.
     *
     * @param tasks list of tasks already added.
     * @param taskNumber number assigned to task in the list.
     */
    private static void printMarkedAcknowledgement(ArrayList<Todo> tasks, int taskNumber) {
        System.out.println("    _________________________________________");
        System.out.println("    " + taskNumber + "." + "[X] " + tasks.get(taskNumber - 1).getDescription());
        System.out.println("    _________________________________________");
    }

    /**
     * Returns task number.
     *
     * @return task number.
     */
    private static int getTaskNumber(String task) {
        return Integer.parseInt(task);
    }

    /**
     * Prints list contents.
     *
     * @param tasks list of tasks already added.
     * @param counter number of tasks in the list.
     */
    private static void printListContents(ArrayList<Todo> tasks, int counter) {
        for (int i = 0; i < counter; ++i) {
            if (tasks.get(i).isDone) {
                System.out.print("    " + (i + 1) + ".");
                tasks.get(i).printInList();
            } else {
                System.out.print("    " + (i + 1) + ".");
                tasks.get(i).printInList();
            }
        }
        System.out.println("    _________________________________________");
        System.out.println("     ");
    }
}
