package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.IllegalCommand;
import duke.command.ListCommand;
import duke.command.MarkAndUnmarkCommand;
import duke.exception.EmptyCommandException;
import duke.exception.IllegalCommandException;

/**
 * Parser object that deals with making sense of the user command
 */
public class Parser {

    public static final int FIRST_WORD_INDEX = 0;

    /**
     * Returns a Command object that will be used by Duke to run the command
     *
     * @param fullCommand the full input from the user in one line
     * @param tasks       the current TaskList to be referenced from
     * @return the type of command corresponding to the first word of user input, where an illegal command will be
     * given when it does not match any of the supported commands by Duke
     */
    public static Command parse(String fullCommand, TaskList tasks) {
        String firstWord = fullCommand.split(" ")[FIRST_WORD_INDEX];
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
            return markOrUnmarkHandler(fullCommand, tasks);
        } else if (firstWord.equals("todo")) {
            return todoTaskHandler(fullCommand);
        } else if (firstWord.equals("deadline")) {
            return deadlineTaskHandler(fullCommand);
        } else if (firstWord.equals("event")) {
            return eventTaskHandler(fullCommand);
        } else if (firstWord.equals("delete")) {
            return deleteTaskHandler(fullCommand, tasks);
        } else if (firstWord.equals("find")) {
            return findTaskHandler(fullCommand);
        } else {
            return new IllegalCommand();
        }
    }

    /**
     * Returns the Command object for mark and unmark commands
     * Returns an illegal command if there is an error in the format
     * Handles the mark and unmark type of commands and outputs illegal message if command is illegal
     *
     * @param command the user input command line
     * @param tasks   the current TaskList to be referenced from
     * @return a Command object that is already prepared, ready for use in the execution step
     */
    private static Command markOrUnmarkHandler(String command, TaskList tasks) {
        Command markingCommand;
        try {
            markingCommand = prepareMarkAndUnmarkTask(command, tasks);
        } catch (IllegalCommandException e) {
            return new IllegalCommand();
        }
        return markingCommand;
    }

    /**
     * Returns Command object for mark and unmark commands if successful
     * Does input preparation where it separates the key commands from the user input into the mark or unmark Command
     *
     * @param command the user input command line
     * @param tasks   the current TaskList to be referenced from
     * @return the prepared mark or unmark Command
     * @throws IllegalCommandException if format of command is wrong, or when index is out of bounds of the TaskList
     */
    private static Command prepareMarkAndUnmarkTask(String command, TaskList tasks) throws IllegalCommandException {
        String[] words = command.split(" ");
        if (words.length != 2) {
            throw new IllegalCommandException();
        }
        int indexOfMarking = getIndex(words[1]);
        if (!isValidIndex(indexOfMarking, tasks)) {
            throw new IllegalCommandException();
        }
        return new MarkAndUnmarkCommand(command, words, indexOfMarking);
    }

    /**
     * Returns the Todo type Command that has been already prepared or null if there is an error
     * Handles the todo type of command and outputs an empty command message when the description is empty
     *
     * @param fullCommand the user input command line
     * @return a todo Command type or null is there is an error
     */
    private static Command todoTaskHandler(String fullCommand) {
        Command todoCommand = null;
        try {
            todoCommand = prepareTodoTask(fullCommand);
        } catch (EmptyCommandException e) {
            Ui.emptyCommandMessage("todo");
        }
        return todoCommand;
    }

    /**
     * Returns the Todo type Command that has been already prepared
     * Does input preparation where it separates the key commands from the user input into the todo Command type
     *
     * @param fullCommand the user input command line
     * @return the todo Command if it is successful
     * @throws EmptyCommandException whenever the todo task description is empty
     */
    private static Command prepareTodoTask(String fullCommand) throws EmptyCommandException {
        String todo = fullCommand.replaceFirst("todo", "").trim();
        if (todo.isEmpty()) {
            throw new EmptyCommandException();
        }
        return new AddTodoCommand(todo);
    }

    /**
     * Returns the deadline type command that has been already prepared
     * Returns an illegal command if there is an error in the format
     * Returns null if there is an empty description
     * Handles the deadline type of command and outputs an empty command message when the description is empty
     * or an illegal command message when the format of the deadline is wrong
     *
     * @param fullCommand the user input command line
     * @return a Command object or null if there is an empty description
     */
    private static Command deadlineTaskHandler(String fullCommand) {
        Command deadlineCommand = null;
        try {
            deadlineCommand = prepareDeadlineTask(fullCommand);
        } catch (EmptyCommandException e) {
            Ui.emptyCommandMessage("deadline");
        } catch (IllegalCommandException e) {
            return new IllegalCommand();
        }
        return deadlineCommand;
    }

    /**
     * Returns the deadline type command that has been already prepared
     * Does input preparation where it separates the key commands from the user input into the deadline Command type
     *
     * @param fullCommand the user input command line
     * @return the deadline command if successful
     * @throws EmptyCommandException   when description of deadline is empty
     * @throws IllegalCommandException when the string array length is not 2 after splitting as the format is wrong
     */
    private static Command prepareDeadlineTask(String fullCommand)
            throws EmptyCommandException, IllegalCommandException {
        fullCommand = fullCommand.replaceFirst("deadline", "").trim();
        if (fullCommand.isEmpty()) {
            throw new EmptyCommandException();
        }
        String[] stringSplit = fullCommand.split(" /by ");
        if (isInvalidString(stringSplit)) {
            throw new IllegalCommandException();
        }
        return new AddDeadlineCommand(stringSplit[0], stringSplit[1]);
    }

    /**
     * Returns the event type command that has been already prepared
     * Returns an illegal command if there is an error in the format
     * Returns null when the description of the event is empty
     * Handles the event type of command and outputs an empty command message when the description is empty
     * or an illegal command message when the format is wrong
     *
     * @param fullCommand the user input command line
     * @return the a command type or null if description is empty
     */
    private static Command eventTaskHandler(String fullCommand) {
        Command eventCommand = null;
        try {
            eventCommand = prepareEventTask(fullCommand);
        } catch (IllegalCommandException e) {
            return new IllegalCommand();
        } catch (EmptyCommandException e) {
            Ui.emptyCommandMessage("event");
        }
        return eventCommand;
    }

    /**
     * Returns the event type command that has been already prepared
     * Does input preparation where it separates the key commands from the user input into the event Command type
     *
     * @param fullCommand the user input command line
     * @return the event command type if successful
     * @throws IllegalCommandException whenever there the string array is not of length 2 after splitting
     * @throws EmptyCommandException   whenever the description of the event command is empty
     */
    private static Command prepareEventTask(String fullCommand) throws IllegalCommandException, EmptyCommandException {
        fullCommand = fullCommand.replaceFirst("event", "").trim();
        if (fullCommand.isEmpty()) {
            throw new EmptyCommandException();
        }
        String[] stringSplit = fullCommand.split(" /from ");
        if (isInvalidString(stringSplit)) {
            throw new IllegalCommandException();
        }
        String[] startToEndTime = stringSplit[1].split(" /to ");
        if (isInvalidString(startToEndTime)) {
            throw new IllegalCommandException();
        }
        return new AddEventCommand(stringSplit[0], startToEndTime[0], startToEndTime[1]);
    }

    /**
     * Returns the Delete type command that has been already prepared if successful
     * Returns an Illegal type command if there is a incorrect format type
     * Handles the event type of command and outputs an illegal command message when there is an exception
     *
     * @param fullCommand the user input command line
     * @param tasks       the current TaskList to be referenced from
     * @return a type of Command to be executed by Duke
     */
    private static Command deleteTaskHandler(String fullCommand, TaskList tasks) {
        Command deleteCommand;
        try {
            deleteCommand = prepareDeleteTask(fullCommand, tasks);
        } catch (IllegalCommandException e) {
            return new IllegalCommand();
        }
        return deleteCommand;
    }

    /**
     * Returns the Delete type command that is prepared
     * Does input preparation where it separates the key commands from the user input into the delete Command type
     *
     * @param fullCommand the user input command line
     * @param tasks       the current TaskList to be referenced from
     * @return the Delete command typethe user input command line
     * @throws IllegalCommandException when index is out of bounds or when string length is not 2 after splitting
     */
    private static Command prepareDeleteTask(String fullCommand, TaskList tasks) throws IllegalCommandException {
        String[] words = fullCommand.trim().split(" ");
        if (isInvalidString(words)) {
            throw new IllegalCommandException();
        }
        int deleteIndex = getIndex(words[1]);
        if (!isValidIndex(deleteIndex, tasks)) {
            throw new IllegalCommandException();
        }
        return new DeleteTaskCommand(deleteIndex);
    }

    /**
     * Returns the Find type Command if preparation is successful
     * Returns an Illegal type command if there is an incorrect format type
     * Handles the event type of finding through preparation
     *
     * @param fullCommand the user input command line
     * @return
     */
    private static Command findTaskHandler(String fullCommand) {
        Command findCommand;
        try {
            findCommand = prepareFindTask(fullCommand);
        } catch (IllegalCommandException e) {
            return new IllegalCommand();
        }
        return findCommand;
    }

    /**
     * Returns the find type command after prepared with the corresponding keyword (search word)
     *
     * @param fullCommand the user input command line
     * @return a new FindCommand object if successful
     * @throws IllegalCommandException when the keyword is empty
     */
    private static Command prepareFindTask(String fullCommand) throws IllegalCommandException {
        String keyword = fullCommand.replaceFirst("find", "").trim();
        if (keyword.isEmpty()) {
            throw new IllegalCommandException();
        }
        return new FindCommand(keyword);
    }


    /**
     * Returns true is the 0-indexed marking is within the array boundaries of 0 and size-1, false otherwise
     *
     * @param indexOfMarking the index to be checked if it is within the TaskList length
     * @param tasks          the current TaskList's length to be referenced from
     * @return a boolean value to allow the person to know if their index is within array range
     */
    private static boolean isValidIndex(int indexOfMarking, TaskList tasks) {
        if (indexOfMarking < 0 || indexOfMarking > (tasks.getTaskCount() - 1)) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if length of the input string array is not equals to 2, false otherwise
     *
     * @param stringSplit a String array to be referenced from which is used after splitting of a string for formatting
     * @return a boolean value on whether the string array given is a valid string array
     */
    private static boolean isInvalidString(String[] stringSplit) {
        return stringSplit.length != 2;
    }

    /**
     * Returns 0-index of parsing Integer
     * Returns -1 if string is not a number or empty
     *
     * @param strNum the input String to test if it is a number or not a number
     * @return an integer value of either -1 if the string is invalid, or a 0-indexed integer when the string is valid
     */
    private static int getIndex(String strNum) {
        // Referenced from https://www.baeldung.com/java-check-string-number
        int index = 0;
        if (strNum == null) {
            return -1;
        }
        try {
            index = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return -1;
        }
        index--;
        return index;
    }

}
