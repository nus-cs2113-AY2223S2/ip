package duke.input;

import duke.tasks.*;
import duke.exceptions.*;
import duke.files.FileManager;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    final String BARRIER = "____________________________________________________________";

    public void handleInput(String input) {
        // Split user input to check for dynamism
        String originalInput = input;
        String[] splitInput = input.split(" ");
        input = splitInput[0];
        int numTasks = TaskList.getTasksArray().size();

        // Switch to check input
        switch(input){

        // Lists all tasks using the Task class printAllTask() method
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

        // Marks a task as complete
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

        // Unmarks a previous completed task
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
                if(splitInput.length != 2) {
                    throw new DukeExceptions.invalidInputStructure("Argh! Structure ye input correctly!");
                }
                System.out.println(BARRIER + "\n");
                Todo temp = new Todo(originalInput);
                System.out.println("added: " + temp.printTask());
                printListLength();
                System.out.println(BARRIER + "\n");
            } finally {
                break;
            }
        }

        // Adds a new Deadline task
        case "deadline": {
            try {
                String[] slashInput = originalInput.split("/");
                if(slashInput.length != 2) {
                    throw new DukeExceptions.invalidInputStructure("Argh! Structure ye input correctly!");
                }
                // Command input starts after '/'
                int commandStart = originalInput.indexOf("/");
                // Due date starts after '/by '
                String dueDate = originalInput.substring(commandStart + 4);
                String parsedDescription = originalInput.substring(0, commandStart - 1);
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

        // Adds a new Event task
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
                // End time comes after '/to'
                String end = splitEventInput[2].substring(3);
                String parsedDescription = splitEventInput[0] + "(from: " + start + "to: " + end + ")";
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

        // Adds a new task if no case is hit
        default: {
            System.out.println(BARRIER + "\n");
            System.out.println("Argh! This is not a valid order, type 'help' for assistance!");
            System.out.println(BARRIER + "\n");
        }
        // End of switch
        }
    }
    public static void printHelpList() {
        String BARRIER = "____________________________________________________________";
        System.out.println(BARRIER + "\n\nAvast! Here be the commands ye can use to make me do yer bidding!" + 
                                    "\n- list: lists all current tasks\n- mark x: marks task x as complete\n-" + 
                                    "unmark x: unmarks task x as complete\n- todo 'description': adds a task to do " + 
                                    "with the given description\n- deadline 'description' /by 'deadline': adds a deadline" + 
                                    " task with the given date and description\n- event 'description' /from 'start' /to 'end': " +
                                    "adds an event with the start and endtime\n- bye: exits Duke\n- anything else: " + 
                                    "adds a basic task with the given description\n" + BARRIER + "\n");
    }

    public static void printListLength() {
        int length = TaskList.getTasksArray().size();
        if (length == 1) {
            System.out.println("Now you have " + TaskList.getTasksArray().size() + " task in the list!");
        } else {
            System.out.println("Now you have " + TaskList.getTasksArray().size() + " tasks in the list!");
        }
    }

    public static boolean isValidInput(String input) {
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
}