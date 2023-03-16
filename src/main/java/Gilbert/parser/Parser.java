package Gilbert.parser;

import Gilbert.commands.*;

import Gilbert.exceptions.GilbertException;

import Gilbert.tasks.TaskList;

import java.util.Scanner;

import Gilbert.messages.*;

public class Parser {
    private static final int COMMAND_INDEX = 0;
    private static final int DESC_INDEX = 1;
    private static Scanner scanner = new Scanner(System.in);
    public static boolean runStatus = true;

    /**
     * Checks whether the exit command has been used.
     *
     * @return false when exit command has been given.
     */
    public static boolean isExit() {
        return runStatus;
    }

    /**
     * Takes in a user input and returns a String array.
     *
     * @return User input split into 2 parts.
     */

    public static String[] userInput() {
        String line = scanner.nextLine();
        String[] input = line.split(" ", 2);
        return input;
    }

    /**
     * Takes in the parsed user input and checks which valid command to execute.
     *
     * @param taskList          A list of all the current Gilbert.tasks.
     * @param input             Takes in the user input in a String array.
     */

    public Commands executeCommand(TaskList taskList, String[] input) throws GilbertException {
        String commandType = input[COMMAND_INDEX].toLowerCase();
        String description = "";
        if (input.length > 1) {
            description = input[DESC_INDEX];
        }
        try {
            switch (commandType) {
                case "todo":
                    Commands addTodo = new CommandAddTodo();
                    addTodo.doCommand(taskList, description);
                    break;
                case "deadline":
                    Commands addDeadline = new CommandAddDeadline();
                    addDeadline.doCommand(taskList, description);
                    break;
                case "event" :
                    Commands addEvent = new CommandAddEvent();
                    addEvent.doCommand(taskList, description);
                    break;
                case "list" :
                    Commands listOut = new CommandList();
                    listOut.doCommand(taskList, description);
                    break;
                case "mark":
                    Commands markDone = new CommandMark();
                    markDone.doCommand(taskList, description);
                    break;
                case "unmark":
                    Commands markUndone = new CommandUnmark();
                    markUndone.doCommand(taskList, description);
                    break;
                case "delete":
                    Commands deleteTask = new CommandDelete();
                    deleteTask.doCommand(taskList, description);
                    break;
                case "help":
                    Commands helpCommand = new CommandHelp();
                    helpCommand.doCommand(taskList, description);
                    break;
                case "find":
                    Commands findTask = new CommandFind();
                    findTask.doCommand(taskList, description);
                    break;
                case "bye":
                    runStatus = false;
                    break;
                default:
                    System.out.println(Messages.ERROR);
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Messages.OUTOFBOUNDS);
        }
        return null;
    }
}

