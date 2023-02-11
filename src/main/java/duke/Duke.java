package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;

import error.DukeAlreadyMarkedException;
import error.DukeIllegalCommandException;
import error.DukeIllegalSyntaxException;
import error.DukeTaskDoesNotExistException;
import error.DukeIllegalCharacterException;

public class Duke {

    static final String HORIZONTAL_LINE = "____________________________________________________________";
    static ArrayList<Task> tasks = FileOperations.loadArrayListFromFile();

    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void runProgram() {

        // Variables needed
        Scanner scanner = new Scanner(System.in);
        String userInput;
        String command;

        // Get first task
        userInput = scanner.nextLine();
        command = userInput.split(" ")[0];


        // Loop for next tasks to add
        while (!userInput.equals("bye")) {

            try {

                System.out.println(HORIZONTAL_LINE);

                if (userInput.contains("|")) {
                    throw new DukeIllegalCharacterException();
                }

                // Print list upon user request
                if (userInput.equals("list")) {
                    printList(tasks);
                } else if (userInput.startsWith("mark") || userInput.startsWith("unmark")
                        || userInput.startsWith("delete")) {

                    int taskIndex = Integer.parseInt(userInput.substring(userInput.length() - 1));

                    // If task does not exist, throw exception
                    if (taskIndex > tasks.size() || taskIndex < 0) {
                        throw new DukeTaskDoesNotExistException();
                    }

                    // Mark as done
                    if (userInput.startsWith("mark") && userInput.charAt(4) == ' ') {
                        tasks.get(taskIndex - 1).markAsDone();
                    }

                    // Mark as undone
                    else if (userInput.startsWith("unmark") && userInput.charAt(6) == ' ') {
                        tasks.get(taskIndex - 1).markAsNotDone();
                    }

                    // Delete task from array
                    else if (userInput.startsWith("delete")) {
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println(taskIndex + "." + tasks.get(taskIndex - 1).toString());
                        tasks.remove(taskIndex - 1);
                        printNumberOfTasks();
                    }

                }
                // Add other tasks into list
                else {

                    Task newTask;

                    // Depending on the type of command, the input gets parsed into the different handlers
                    switch (command) {
                    case "deadline":
                        String[] deadlineArgs = deadlineHandler(userInput);
                        newTask = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                        break;
                    case "event":
                        String[] eventArgs = eventHandler(userInput);
                        newTask = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
                        break;
                    case "todo":
                        String todoCommand = todoHandler(userInput);
                        newTask = new Todo(todoCommand);
                        break;
                    default:
                        throw new DukeIllegalCommandException();
                    }

                    tasks.add(newTask);
                    System.out.println("Got it. I've added this task: \n" + newTask);
                    printNumberOfTasks();

                }

                // Save task ArrayList information into tasks.txt
                FileOperations.saveArrayListToFile(tasks);

            }

            // This runs when the user enters an invalid command
            catch (DukeIllegalCommandException exception) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            // This runs when a user enters the wrong syntax for a command
            catch (DukeIllegalSyntaxException exception) {
                System.out.println("☹ OOPS!!! I'm sorry, You entered an incorrect syntax :-(");
            }

            // This runs when a user tries to mark or unmark a task that already has that marking
            catch (DukeAlreadyMarkedException exception) {
                System.out.println("☹ OOPS!!! This task already has this marking :-(");
            }

            // This runs when a user tries to delete a non-existent task index number
            catch (DukeTaskDoesNotExistException exception) {
                System.out.println("☹ OOPS!!! This task does not exist :-(");
            }

            // This runs when a user enters `|` in their input
            catch (DukeIllegalCharacterException exception) {
                System.out.println("☹ OOPS!!! Please do not have '|' in your input :-(");
            }

            // This runs when there is an unexpected error with file reading/writing
            catch (IOException exception) {
                System.out.println("☹ OOPS!!! Read/write file error :-(");
            }

            // Enter the next input and continue the loop
            finally {
                // Print trailing horizontal line and take in next input
                System.out.println(HORIZONTAL_LINE);
                userInput = scanner.nextLine();
                command = userInput.split(" ")[0];
            }
        }

    }

    public static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
        System.exit(0);
    }

    public static void printList(ArrayList<Task> listOfInputs) {
        int counter = 0;
        for (Task inputs : listOfInputs) {
            System.out.println(counter + 1 + "." + inputs.toString());
            counter++;
        }
    }

    public static void printNumberOfTasks() {
        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    // Returns a String containing taskName
    public static String todoHandler(String userInput) {

        // Format of userInput: <command> <task_name>
        String taskName = userInput.replaceFirst("todo ", "");
        return taskName;

    }

    // Returns a String array containing {taskName, deadline}
    public static String[] deadlineHandler(String userInput) throws DukeIllegalSyntaxException {

        // Format of userInput: <command> <taskName> /by <deadline>
        int numberOfWords = userInput.split(" ").length - 1;
        int endOfTaskNameIndex = 0;
        String[] userInputArray = new String[numberOfWords];
        String[] outputArray = {"", ""};

        userInput = userInput.replaceFirst("deadline ", "");

        // Checks if userInput contains "\by"
        if (!userInput.contains("/by")) {
            throw new DukeIllegalSyntaxException();
        }

        // Get index of "/by"
        userInputArray = userInput.split(" ");
        for (int i = 0; i < numberOfWords; i++) {
            if (userInputArray[i].equals("/by")) {
                endOfTaskNameIndex = i;
                break;
            }
        }

        // Add the taskName into index 0 of outputArray
        for (int i = 0; i < endOfTaskNameIndex; i++) {
            outputArray[0] += userInputArray[i] + " ";
        }
        outputArray[0] = outputArray[0].trim();

        // Add the deadline into index 1 of outputArray
        for (int i = endOfTaskNameIndex + 1; i < numberOfWords; i++) {
            outputArray[1] += userInputArray[i] + " ";
        }
        outputArray[1] = outputArray[1].trim();

        // Return {taskName, deadline}
        return outputArray;

    }

    // Returns a String array containing {taskName, from, to}
    public static String[] eventHandler(String userInput) throws DukeIllegalSyntaxException {

        // Format of userInput: <command> <taskName> /from <from> /to <to>
        int numberOfWords = userInput.split(" ").length - 1;
        int fromIndex = 0;
        int toIndex = 0;
        String[] userInputArray;
        String[] outputArray = {"", "", ""};
        boolean isFromPassed = false;
        boolean isToPassed = false;

        // Checks if userInput contains "\from" and "\to"
        if (!userInput.contains("/from") || !userInput.contains("/to")) {
            throw new DukeIllegalSyntaxException();
        }

        // Remove `event` from
        userInput = userInput.replaceFirst("event ", "");
        userInputArray = userInput.split(" ");

        // Get indexes of `/from` and `/to` in userInput
        for (int i = 0; i < numberOfWords; i++) {
            if (userInputArray[i].equals("/from") && !isFromPassed) {
                fromIndex = i;
                isFromPassed = true;
            }
            if (userInputArray[i].equals("/to") && !isToPassed) {
                toIndex = i;
                isToPassed = true;
            }
        }

        // Add the taskName into index 0 of outputArray
        for (int i = 0; i < fromIndex; i++) {
            outputArray[0] += userInputArray[i] + " ";
        }
        outputArray[0] = outputArray[0].trim();

        // Add the `from` field into index 1 of outputArray
        for (int i = fromIndex + 1; i < toIndex; i++) {
            outputArray[1] += userInputArray[i] + " ";
        }
        outputArray[1] = outputArray[1].trim();

        // Add the `to` field into index 2 of outputArray
        for (int i = toIndex + 1; i < numberOfWords; i++) {
            outputArray[2] += userInputArray[i] + " ";
        }
        outputArray[2] = outputArray[2].trim();

        return outputArray;

    }

    public static void main(String[] args) {

        // Start the program with a lovely greeting
        greet();

        // Run the program
        runProgram();

        // Exit the program
        exit();

    }

}