package userInputParser;

import ui.exceptions.MissingCommandException;
import ui.Display;

import commandHandler.Add;
import commandHandler.Delete;
import commandHandler.Find;
import commandHandler.List;
import commandHandler.Mark;
import commandHandler.Help;
import data.tasksList;

/**
 * Represents the parser used to parse user input and execute corresponding
 * functions.
 */
public class Parser {
    private static final String COMMAND_BYE = "/bye";
    private static final String COMMAND_LIST = "/list";
    private static final String COMMAND_MARK = "/mark";
    private static final String COMMAND_UNMARK = "/unmark";
    private static final String COMMAND_EVENT = "/event";
    private static final String COMMAND_TODO = "/todo";
    private static final String COMMAND_DEADLINE = "/deadline";
    private static final String COMMAND_DELETE = "/delete";
    private static final String COMMAND_FIND = "/find";
    private static final String COMMAND_HELP = "/help";

    public enum MarkType {
        MARK, UNMARK
    }

    /**
     * Takes in user input as a string and parses it into the corresponding commands
     * and additional arguments if any. The corresponding functions that match the
     * user specified feature will then be executed. If any required arguments are
     * missing, a MissingCommandException is thrown.
     * 
     * @param userInput Contains command and additional arguments if any.
     * @throws MissingCommandException If required arguments are missing.
     * @see MissingCommandException
     */
    public static void parseUserInput(String userInput) throws MissingCommandException {
        String[] userInputArray = userInput.split(" ", 2);
        String command = userInputArray[0];
        String arguments;
        switch (command) {
            case COMMAND_BYE:
                Display.goodbyeUser();
                break;
            case COMMAND_LIST:
                List.listTasks();
                break;
            case COMMAND_HELP:
                Help.displayHelp();
                break;
            case COMMAND_TODO:
            case COMMAND_DEADLINE:
            case COMMAND_EVENT:
                if (userInputArray.length < 2) {
                    throw new MissingCommandException("Please enter the required arguments!");
                }
                arguments = userInputArray[1];
                Add.addTask(command, arguments);
                Display.notifyUser(
                        "Added the following task:\n" +
                                tasksList.userTasksList.get(tasksList.userTaskCount - 1));
                break;
            case COMMAND_FIND:
                if (userInputArray.length < 2) {
                    throw new MissingCommandException("Please enter the required arguments!");
                }
                arguments = userInputArray[1];
                new Find(arguments);
                break;
            case COMMAND_MARK:
                if (userInputArray.length < 2) {
                    throw new MissingCommandException("Please enter the required arguments!");
                }
                arguments = userInputArray[1];
                Mark.markTask(arguments, MarkType.MARK);
                break;
            case COMMAND_UNMARK:
                if (userInputArray.length < 2) {
                    throw new MissingCommandException("Please enter the required arguments!");
                }
                arguments = userInputArray[1];
                Mark.markTask(arguments, MarkType.UNMARK);
                break;
            case COMMAND_DELETE:
                if (userInputArray.length < 2) {
                    throw new MissingCommandException("Please enter the required arguments!");
                }
                arguments = userInputArray[1];
                try {
                    Delete.deleteTask(Integer.parseInt(arguments));
                } catch (Exception e) {
                    Display.warnUser("Please enter a valid numerical index of the task!");
                }
                break;
            default:
                throw new MissingCommandException("Please enter a valid command!");
        }
    }
}
