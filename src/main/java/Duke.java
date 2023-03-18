import duke.Deadline;
import duke.Event;
import duke.Todo;
import duke.Ui;
import duke.Storage;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) throws FileNotFoundException {

        Ui ui = new Ui();
        Storage storage = new Storage();

        ui.showGreetingMessage();

        //Read in input from user
        String inputString;

        //Set up list to store user inputs
        ArrayList<Todo> tasks = new ArrayList<>();
        int counter = 0;


        //setup of exit flag
        boolean exit = false;

        //Initialize list with saved data
        String absoluteFilePath = storage.findFilePath();
        counter = storage.initializeList(tasks, counter, absoluteFilePath);

        while (!exit) {
            String taskType;
            String task = null;

            inputString = getInputString();

            //Parsing user input
            int descriptionPosition = inputString.indexOf(" ");
            if (descriptionPosition == -1) {
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
                ui.showLine();
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
                System.out.println("    Now you have " + counter + " tasks in your list!");
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
                System.out.println("    Now you have " + counter + " tasks in your list!");
                break;

            case "delete":
                taskNumber = getTaskNumber(task) - 1;
                ui.showLine();
                tasks.get(taskNumber).printInList();
                ui.showLine();
                System.out.println("    ");
                tasks.remove(tasks.get(taskNumber));
                counter--;
                break;

            case "find":
                String keyword = task;
                for (int i = 0; i < tasks.size(); i++) {
                    String taskDescription = tasks.get(i).getDescription();
                    if (taskDescription.contains(task)) {
                        System.out.print("    " + (tasks.indexOf(tasks.get(i)) + 1) + ".");
                        tasks.get(i).printInList();
                    }
                }

            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
        }

        //When user types "bye"
        ui.showExitMessage();
        storage.writeToFile(tasks, absoluteFilePath, counter);
    }

    /**
     * Marks a task as done.
     *
     * @param tasks     list of tasks already added.
     * @param fw        file to be modified.
     * @param i         increment for loop.
     * @param classType type of task.
     */


    /**
     * Marks a task as done.
     *
     * @param tasks      list of tasks already added.
     * @param taskNumber number assigned to task in the list.
     */
    private static void markAsDone(ArrayList<Todo> tasks, int taskNumber) {
        tasks.get(taskNumber - 1).setDone(true);
    }


    /**
     * Returns counter after initializing list with items that were saved to disk previously.
     * If no such saved file exists, a new file will be created on disk to save list items
     * upon exiting the program.
     *
     * @param tasks   list of tasks already added.
     * @param counter number of tasks in the list.
     * @return counter number of tasks in the list.
     * @throws FileNotFoundException If .txt file is not found at specified file path.
     */

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
     * @param tasks      list of tasks already added.
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
     * @param tasks      list of tasks already added.
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
     * @param tasks   list of tasks already added.
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
