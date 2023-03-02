import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.Todo;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;


/*
Things to do:

- copy refactored functions into created classes

- Create proper parser

- Setup text file reading at startup
    -use Scanner to read in text file
    -use .nextline to read in each line
 */


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

        while (!exit) {
            inputString = getInputString();
            int descriptionPosition = inputString.indexOf("~");
            int endPosition = inputString.length();
            String taskType = inputString.substring(0, descriptionPosition);
            String task = inputString.substring(descriptionPosition + 1, endPosition);


            //switch cases for all specified input types
            switch (taskType) {
            case "bye":
                exit = true;
                break;

            case "list":
                System.out.println("    _________________________________________");
                printListContents(tasks, counter);
                break;

            case "mark":
                System.out.println("    Please specify task number: ");
                int taskNumber = getTaskNumber();
                markAsDone(tasks, taskNumber);
                printMarkedAcknowledgement(tasks, taskNumber);
                break;

            case "unmark":
                System.out.println("    Please specify task number: ");
                taskNumber = getTaskNumber();
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
                System.out.println("    Please specify task number: ");
                taskNumber = getTaskNumber() - 1;
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

        PrintWriter fw = new PrintWriter("out\\list.txt");
        for (int i = 0; i < counter; i++) {
            fw.println(tasks.get(i).getClass() + " | " + tasks.get(i).getDescription() + " | " + tasks.get(i).isDone + " | " + tasks.get(i).getBy() + " | " + tasks.get(i).getEnd());
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

    /**
     * Returns user input as a string.
     *
     * @return input string.
     */
    private static String getInputString(){
        Scanner in;
        String inputString;
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
    private static int getTaskNumber() {
        String inputString = getInputString();
        return Integer.parseInt(inputString);
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
