package userInputParser;

import ui.exceptions.MissingCommandException;
import ui.Display;
import commandHandler.*;

public class Parser {
    private static final String COMMAND_BYE = "/bye";
    private static final String COMMAND_LIST = "/list";
    private static final String COMMAND_MARK = "/mark";
    private static final String COMMAND_UNMARK = "/unmark";
    private static final String COMMAND_EVENT = "/event";
    private static final String COMMAND_TODO = "/todo";
    private static final String COMMAND_DEADLINE = "/deadline";
    private static final String COMMAND_DELETE = "/delete";

    public enum taskType {
        TODO, DEADLINE, EVENT
    }

    public enum markType {
        MARK, UNMARK
    }

    public static void parseUserInput(String userInput) {
        /** Handle single-word input commands with no arguments **/
        if (userInput.equals(COMMAND_BYE)) {
            Display.notifyUser(Display.MESSAGE_GOODBYE);
            return;
        }
        if (userInput.equals(COMMAND_LIST)) {
            List.listTasks();
        } else {
            /** Handle multi-word input commands with required arguments **/
            String[] userInputArray = userInput.split(" ");
            String command = userInputArray[0];
            if (command.equals(COMMAND_TODO) || command.equals(COMMAND_EVENT) || command.equals(COMMAND_DEADLINE)) {
                try {
                    Add.addTask(userInput);
                } catch (MissingCommandException e) {
                    Display.warnUser(e.getMessage());
                }
            } else if (command.equals(COMMAND_MARK)) {
                Mark.markTask(userInputArray, markType.MARK);
            } else if (command.equals(COMMAND_UNMARK)) {
                Mark.markTask(userInputArray, markType.UNMARK);
            } else if (command.equals(COMMAND_DELETE)) {
                Delete.deleteTask(Integer.parseInt(userInputArray[1]));

            } else {
                /** Handle non-command inputs **/
                Display.warnUser("Please enter a valid command!");
            }
        }
    }
}
