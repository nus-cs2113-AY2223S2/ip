package duke.input;

import duke.tasks.*;
import duke.exceptions.*;
import duke.files.FileManager;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    final static String BARRIER = "____________________________________________________________";

    /**
     * Takes input from a user, parses it, and executes based on input.
     * @param input the command submitted by the user.
     * @throws noTasksException if a user prints the list with no tasks
     * @throws invalidInputStructure if a user structures command syntax incorrectly
     * @throws invalidNumberException if a user submits a non-numeric value incorrectly
     */
    public void handleInput(String input) {

        // Split user input to check for dyanmic input
        String originalInput = input;
        String[] splitInput = input.split(" ");
        input = splitInput[0];
        int numTasks = TaskList.getTasksArray().size();

        // Switch to check input
        switch(input){

        /**
         * Lists all tasks using the TaskList class printAllTask() method.
         * No interaction with dynamic input.
         */
        case "list": {
            try {
                if(numTasks == 0) { 
                    throw new DukeExceptions.noTasksException("Ye have no tasks yet, ye lazy buccaneer!");
                }
                if(splitInput.length > 1) {
                    throw new DukeExceptions.invalidInputStructure("Argh! Structure ye input correctly!");
                }
                System.out.println(BARRIER + "\n");
                TaskList.printAllTasks();
                System.out.println(BARRIER + "\n");
            } catch (DukeExceptions.noTasksException e) {
                System.out.println(BARRIER + "\n");
            } finally {
                break;
            }
        }

        /**
         * Marks a task as complete. Throws an error if the user tries to submit a
         * task number that is non-positive, non-numeric, or out or rnage of the list.
         */
        case "mark": {
            try {
                if (!isValidInput(splitInput[1])) {
                    throw new DukeExceptions.invalidNumberException(BARRIER + "\n\nBlast! That isn't a valid number lad!\n" + BARRIER + "\n");
                }
                if(splitInput.length != 2) {
                    throw new DukeExceptions.invalidInputStructure("Argh! Structure ye input correctly!");
                }
                int index = Integer.parseInt(splitInput[1]) - 1;
                TaskList.getTasksArray().get(index).markAsComplete();
            } finally {
                break;
            }
        }

        /**
         * Marks a task as incomplete. Throws an error if the user tries to submit a
         * task number that is non-positive, non-numeric, or out or rnage of the list.
         */
        case "unmark": {
            try {
                if (!isValidInput(splitInput[1])) {
                    throw new DukeExceptions.invalidNumberException(BARRIER + "\n\nBlast! That isn't a valid number lad!\n" + BARRIER + "\n");
                }
                if(splitInput.length != 2) {
                    throw new DukeExceptions.invalidInputStructure("Argh! Structure ye input correctly!");
                }
                int index = Integer.parseInt(splitInput[1]) - 1;
                TaskList.getTasksArray().get(index).unmarkAsComplete();
            } finally {
                break;
            }
        }

        // Prints a list of commands and description
        case "help": {
            printHelpList();
            break;
        }

        // Adds a new ToDo task
        case "todo": {
            try {
                System.out.println(BARRIER + "\n");
                Todo temp = new Todo(originalInput);
                System.out.println("added: " + temp.printTask());
                printListLength();
                System.out.println(BARRIER + "\n");
            } finally {
                break;
            }
        }

        /**
         * Creates a new deadline task. This will throw an error if
         * the user does not submit a due date for the deadline.
         */
        case "deadline": {
            try {
                String[] slashInput = originalInput.split("/");
                if(slashInput.length != 2) {
                    throw new DukeExceptions.invalidInputStructure("Argh! Structure ye input correctly!");
                }
                // The date starts after the first '/'
                int dateStart = originalInput.indexOf("/");

                // Due date starts after '/by '
                String dueDate = originalInput.substring(dateStart + 4);
                dueDate = processDate(dueDate);

                String parsedDescription = originalInput.substring(0, dateStart - 1);
                parsedDescription += " (by: " + dueDate + ")";

                // Remove the word 'deadline' from the description
                parsedDescription = parsedDescription.substring(parsedDescription.indexOf(" ") + 1);

                System.out.println(BARRIER + "\n");
                Deadline temp = new Deadline(parsedDescription, dueDate);
                System.out.println("added: " + temp.printTask());
                printListLength();
                System.out.println(BARRIER + "\n");
            } finally {
                break;
            }
        }

        /**
         * Creates a new event task. This will throw an error if
         * the user does not include a start and end time.
         */
        case "event": {
            try {
                String[] slashInput = originalInput.split("/");
                if(slashInput.length != 3) {
                    throw new DukeExceptions.invalidInputStructure("Argh! Structure ye input correctly!");
                }

                // Split input into description, start, and end
                String[] splitEventInput = originalInput.split("/");
                
                // Start time comes after '/from'
                String start = splitEventInput[1].substring(5);
                start = start.substring(0, start.length() - 1);
                start = processDate(start);

                // End time comes after '/to'
                String end = splitEventInput[2].substring(3);
                end = processDate(end);

                String parsedDescription = splitEventInput[0] + "(from: " + start + " to: " + end + ")";
                parsedDescription = parsedDescription.substring(parsedDescription.indexOf(" ") + 1);

                System.out.println(BARRIER + "\n");
                Event temp = new Event(parsedDescription, start, end);
                System.out.println("added: " + temp.printTask());
                printListLength();
                System.out.println(BARRIER + "\n");
            } finally {
                break;
            }
        }

        /**
         * Removes a task based on its number in the list
         * Throws an error if the number is non-positive, non-numeric, or out of range.
         */
        case "delete": {
            try {
                if (!isValidInput(splitInput[1])) {
                        throw new DukeExceptions.invalidNumberException(BARRIER + "\n\nBlast! That isn't a valid number lad!\n" + BARRIER + "\n");
                }
                if(splitInput.length != 2) {
                    throw new DukeExceptions.invalidInputStructure("Argh! Structure ye input correctly!");
                }
                int index = Integer.parseInt(splitInput[1]) - 1;
                System.out.println(BARRIER + "\n\nAye! The task be removed:");
                System.out.println("    " + TaskList.getTasksArray().get(index).printTask());
                TaskList.getTasksArray().remove(index);
                printListLength();
                System.out.println(BARRIER + "\n");
            } finally {
                break;
            }
        }

        /**
         * Creates a subset of tasks containing an inputted keyword.
         */
        case "find": {
            try {
                // Isolate search key
                String searchKey = originalInput.substring(5);

                // Create and populate list of results
                ArrayList<Task> relevantTasks = new ArrayList<>();
                for(int i = 0; i < TaskList.getTasksArray().size(); i++) {
                    if (TaskList.getTasksArray().get(i).description.contains(searchKey)) {
                        relevantTasks.add(TaskList.getTasksArray().get(i));
                    }
                }

                // No results guard clause
                if (relevantTasks.size() == 0) {
                    System.out.println(BARRIER + "\n\n No tasks found!\n" + BARRIER + "\n");
                    break;
                }

                // Output
                System.out.println(BARRIER + "\n\nTasks found: ");
                for(int i = 0; i < relevantTasks.size(); i++) {
                    System.out.println(i+1 + ". " + relevantTasks.get(i).printTask());
                }
                System.out.println(BARRIER+"\n");
            } finally {
                break;
            }
        }


        // Adds a new task if no case is hit
        default: {
            System.out.println(BARRIER + "\n");
            System.out.println("Argh! This is not a valid order, type 'help' for assistance!");
            System.out.println(BARRIER + "\n");
        }
        // End of switch
        }
    }

    /**
     * Prints out a list of commands.
     */
    private static void printHelpList() {
        String BARRIER = "____________________________________________________________";
        System.out.println(BARRIER + "\n\nAvast! Here be the commands ye can use to make me do yer bidding!" + 
                "\n- list: lists all current tasks\n- mark x: marks task x as complete\n-" + 
                "unmark x: unmarks task x as complete\n- todo 'description': adds a task to do " + 
                "with the given description\n- deadline 'description' /by 'deadline': adds a deadline" + 
                " task with the given date and description\n- event 'description' /from 'start' /to 'end': " +
                "adds an event with the start and endtime\n- bye: exits Duke\n- find 'searchKey': " + 
                "shows all relevant tasks\n- delete 'num': removes task 'num' from list\n" + BARRIER + "\n");
    }

    /**
     * Prints the amount of tasks in the list. 
     */
    private static void printListLength() {
        int length = TaskList.getTasksArray().size();
        if (length == 1) {
            System.out.println("Now you have " + TaskList.getTasksArray().size() + " task in the list!");
        } else {
            System.out.println("Now you have " + TaskList.getTasksArray().size() + " tasks in the list!");
        }
    }

    /**
     * Checks if a numeric input is valid. Checks that the dynamic input
     * is non-zero, in the range of the list, and is a numeric value.
     * @param input Dynamic numeric input submitted by the user.
     * @return true if the input is valid, false otherwise.
     */
    private static boolean isValidInput(String input) {
        char dynamicInput = input.charAt(0);
        boolean isOneChar = input.length() == 1;
        if (!isOneChar) {
            return false;
        }
        int index = Integer.parseInt(input) - 1;
        boolean isNumber = Character.isDigit(dynamicInput);
        boolean isZero = dynamicInput == '0';
        boolean isInRange = index <= TaskList.getTasksArray().size() - 1;
        return (isNumber && !isZero && isInRange);
    }

    /**
     * Checks if a date is submitted in the form YYYY-MM-DD.
     * If so, converts it to a more readable format. Otherwise, returns it as is.
     * 
     * @param date The string form of the time.
     * @return The parsed or original date.
     */
    private static String processDate(String date) {
        if (date.length() == 10 && date.charAt(4) == '-' && date.charAt(7) == '-') {
            LocalDate dateObj = LocalDate.parse(date);
            return dateObj.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return date;
        }
    }
}