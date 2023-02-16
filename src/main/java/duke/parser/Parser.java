package duke.parser;

import duke.error.DukeException;

public class Parser { // deals with making sense of the user command
    private static final int DEADLINE_ARGUMENT_LENGTH = 2;
    public static String[] parse(String userInput) {
        String[] arrayOfInput = userInput.split(" ");
        arrayOfInput[0] = arrayOfInput[0].trim();
        return arrayOfInput;
    }

    public static String parseTodoCommand(String input) throws DukeException {
        InputValidity.checkTodo(input);
        return (input.split(" ", 2)[1]).trim(); // returns taskName
    }

    public static String[] parseDeadlineCommand(String input) throws DukeException {
        InputValidity.checkDeadline(input);
        String tempStore = (input.split(" ", 2)[1]).trim();
        String[] taskDetails = tempStore.split(" /by ");
        for (int i = 0; i < DEADLINE_ARGUMENT_LENGTH; i += 1) {
            taskDetails[i] = taskDetails[i].trim();
        }
        return taskDetails; // returns taskName, deadline
    }

    public static String[] parseEventCommand(String input) throws DukeException {
        InputValidity.checkValidEvent(input);
        String tempStore = (input.split(" ", 2)[1]).trim();
        String[] taskDetails = new String[3];
        taskDetails[0] = (tempStore.split(" /from ", 2)[0]).trim();
        tempStore = (tempStore.split(" /from ", 2)[1]).trim();
        taskDetails[1] = (tempStore.split(" /to ", 2)[0]).trim();
        taskDetails[2] = (tempStore.split(" /to ", 2)[1]).trim();
        return taskDetails; // returns taskName, startDate, endDate
    }

    public static String parseCommand(String[] userInput, String command) throws DukeException {
        InputValidity.isValid(userInput, command);
        return userInput[1].trim();
    }
}
