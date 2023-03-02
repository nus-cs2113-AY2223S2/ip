package Alex.parser;

import Alex.command.*;

import java.util.Scanner;


public class Parser {
    private static String[] userInput;
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
     * @return command that will be executed
     */
    public Command parseCommand() {
        userInput = takeInput();
        String commandAction = userInput[0].toLowerCase();

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
            return new IncorrectCommand();
        }
    }

    /**
     * Prepares arguments to execute TodoCommand
     *
     * @return prepared TodoCommand to be executed
     */
    private Command prepareTodo() {
        String activity = "";
        for (int i = 1; i < userInput.length; i++) {
            if (userInput[i].charAt(0) == '/') {
                break;
            } else {
                activity += userInput[i] + " ";
            }
        }
        return new TodoCommand(activity);
    }



    /**
     * Prepares arguments to execute DeadlineCommand
     *
     * @return prepared DeadlineCommand to be executed
     */
    private Command prepareDeadline() {
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

        return new DeadlineCommand(activity, by);
    }

    /**
     * Prepares arguments to execute EventCommand
     *
     * @return prepared EventCommand to be executed
     */
    private Command prepareEvent() {
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
        return new EventCommand(activity,from,to);

    }
    /**
     * Prepares arguments to execute MarkCommand
     *
     * @return prepared MarkCommand to be executed
     */
    private Command prepareMark() {
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
