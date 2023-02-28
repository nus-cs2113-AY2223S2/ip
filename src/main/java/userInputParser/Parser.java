package userInputParser;

import ui.exceptions.MissingCommandException;
import ui.Display;

import commandHandler.Add;
import commandHandler.Delete;
import commandHandler.Find;
import commandHandler.List;
import commandHandler.Mark;
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
        /** Handle single-word input commands with no arguments **/
        if (userInput.equals(COMMAND_BYE)) {
            Display.goodbyeUser();
            return;
        }
        if (userInput.equals(COMMAND_LIST)) {
            List.listTasks();
        } else {
            /** Handle multi-word input commands with required arguments **/
            String[] userInputArray = userInput.split(" ", 2);
            String command = userInputArray[0];
            if (userInputArray.length == 1) {
                throw new MissingCommandException("Please enter the required arguments!");
            }
            String arguments = userInputArray[1];
            if (command.equals(COMMAND_TODO) || command.equals(COMMAND_EVENT) || command.equals(COMMAND_DEADLINE)) {
                Add.addTask(command, arguments);
                Display.notifyUser(
                        "Added the following task:\n" + tasksList.userTasksList.get(tasksList.userTaskCount - 1));
            } else if (command.equals(COMMAND_FIND)) {
                new Find(arguments);
            } else if (command.equals(COMMAND_MARK)) {
                Mark.markTask(arguments, MarkType.MARK);
            } else if (command.equals(COMMAND_UNMARK)) {
                Mark.markTask(arguments, MarkType.UNMARK);
            } else if (command.equals(COMMAND_DELETE)) {
                try {
                    Delete.deleteTask(Integer.parseInt(userInputArray[1]));
                } catch (Exception e) {
                    Display.warnUser("Please enter a valid numerical index of the task!");
                }
            } else {
                /** Handle non-command inputs **/
                Display.warnUser("Please enter a valid command!");
            }
        }
    }
}
