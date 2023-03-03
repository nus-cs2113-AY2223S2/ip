package Alex.parser;

import Alex.command.*;
import Alex.exception.AlexCommandException;
import Alex.exception.AlexTaskException;
import java.util.Scanner;


public class Parser {
    private static String[] userInput;
    private static final String TASK_INDEX_ERROR = "I do not understand your command." +
            " Please check user guide for list of valid commands!";
    private static final String EMPTY_DESCRIPTION = "Please state your description!";
    private static final String EMPTY_BY = "Please state your completion time appropriately!";
    private static final String EMPTY_FROM = "Please state your start time!";
    private static final String EMPTY_TO = "Please state your end time!";
    private static final int INDEX_COMMAND = 0;
    private static Scanner myScanner = new Scanner(System.in);

    /**
     * Takes in user input and returns an array.
     *
     * @return user input in the form of String Array
     */
    public static String[] takeInput() {
        String line = myScanner.nextLine();
        userInput = line.split(" ");
        return userInput;
    }
    /**
     * Parses user input and decides which command to execute.
     *
     * @throw AlexCommandException and AlexTaskException that are command errors and tasks errors
     * @return command that will be executed
     */
    public Command parseCommand() throws AlexCommandException, AlexTaskException  {
        userInput = takeInput();
        String commandAction = userInput[INDEX_COMMAND].toLowerCase();

        switch (commandAction) {
        case TodoCommand.COMMAND_WORD:
            return prepareTodo();
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline();
        case EventCommand.COMMAND_WORD:
            return prepareEvent();
        case MarkCommand.COMMAND_WORD:
            return prepareMark();
        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete();
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case FindCommand.COMMAND_WORD:
            return prepareFind();
        default:
            throw new AlexCommandException(TASK_INDEX_ERROR);
        }
    }

    /**
     * Prepares arguments to execute TodoCommand
     *
     * @return prepared TodoCommand to be executed
     */
    private Command prepareTodo() throws AlexTaskException{
        String activity = "";
        for (int i = 1; i < userInput.length; i++) {
            if (userInput[i].charAt(0) == '/') {
                break;
            } else {
                activity += userInput[i] + " ";
            }
        }
        if (activity.isEmpty()) {
            throw new AlexTaskException("Please state your todo description!");
        }
        return new TodoCommand(activity);
    }



    /**
     * Prepares arguments to execute DeadlineCommand
     *
     * @return prepared DeadlineCommand to be executed
     * @throws AlexTaskException an exception that is related to Tasks tracked by Alex
     */
    private Command prepareDeadline() throws AlexTaskException {
        String activity = "";
        for (int i = 1; i < userInput.length; i++) {
            if (userInput[i].charAt(0) == '/') {
                break;
            } else {
                activity += userInput[i] + " ";
            }
        }
        String by = "";
        int byIndex = 0;
        for(int i = 0; i < userInput.length; i++) {
            if(userInput[i].charAt(0) == '/') {
                byIndex = i;
            }
        }
        for(int i = byIndex + 1; i < userInput.length; i++) {
            by += userInput[i] += " ";
        }
        if (activity.isEmpty()) {
            throw new AlexTaskException(EMPTY_DESCRIPTION);
        }
        if (by.isEmpty() || byIndex == 0) {
            throw new AlexTaskException(EMPTY_BY);
        }

        return new DeadlineCommand(activity, by.substring(0,by.length()-1));
    }

    /**
     * Prepares arguments to execute EventCommand
     *
     * @return prepared EventCommand to be executed
     */
    private Command prepareEvent() throws AlexTaskException{
        String activity = "";
        for (int i = 1; i < userInput.length; i++) {
            if (userInput[i].charAt(0) == '/') {
                break;
            } else {
                activity += userInput[i] + " ";
            }
        }
        String from = "";
        String to = "";
        int fromIndex = 0;
        int toIndex = 0;
        for(int i = 0; i < userInput.length; i++) {
            if(userInput[i].toLowerCase().equals("/from")) {
                fromIndex = i;
            }
            if(userInput[i].toLowerCase().equals("/to")) {
                toIndex = i;
            }
        }
        for(int i = fromIndex + 1; i < toIndex; i++) {
            from += userInput[i] + " ";
        }
        for(int i = toIndex + 1; i < userInput.length; i++) {
            to += userInput[i] + " ";
        }

        if (activity.isEmpty()) {
            throw new AlexTaskException(EMPTY_DESCRIPTION);
        }
        if (from.isEmpty() || fromIndex == 0) {
            throw new AlexTaskException(EMPTY_FROM);
        }
        if (to.isEmpty() || toIndex == 0) {
            throw new AlexTaskException(EMPTY_TO);
        }

        return new EventCommand(activity.substring(0,activity.length()-1),from.substring(0,from.length()-1),to.substring(0,to.length()-1));

    }
    /**
     * Prepares arguments to execute MarkCommand
     *
     * @return prepared MarkCommand to be executed
     */
    private Command prepareMark(){
        int number = Integer.parseInt(userInput[1]);
        return new MarkCommand(number);
    }

    private Command prepareUnmark() {
        int number = Integer.parseInt(userInput[1]);
        return new UnmarkCommand(number);
    }

    private Command prepareDelete() {
        int number = Integer.parseInt(userInput[1]);
        return new DeleteCommand(number);
    }

    private Command prepareFind() {
        String toFind = userInput[1];
        return new FindCommand(toFind);
    }




}
