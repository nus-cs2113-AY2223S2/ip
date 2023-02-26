package duke.functionalities;

import duke.exception.DukeException;

/**
 * This Class deals with making sense of the User Command
 * */
public class Parser {
    protected static Command command;
    protected static String description;
    protected static String notApplicable = null;
    protected static int byIndex;
    protected static int fromIndex;
    protected static int toIndex;

    /**
     * Interprets the User Command to be Executed
     *
     * @param userCommand The Command to be interpreted
     * @throws DukeException if there is an error encountered when interpreting the Duke Command
     * @return The Interpreted Duke Command
     * */
    public static Command parse(String userCommand) throws DukeException {
        String task = userCommand.trim();
        if (task.equalsIgnoreCase("list")) {
            parseListCommand();
        } else if (task.toLowerCase().startsWith("mark")) {
            parseMarkCommand(task);
        } else if (task.toLowerCase().startsWith("unmark")) {
            parseUnmarkCommand(task);
        } else if (task.toLowerCase().startsWith("todo")) {
            parseTodoCommand(task);
        } else if (task.toLowerCase().startsWith("deadline")) {
            parseDeadlineCommand(task);
        } else if (task.toLowerCase().startsWith("event")) {
            parseEventCommand(task);
        } else if (task.toLowerCase().startsWith("delete")) {
            parseDeleteCommand(task);
        } else if (task.toLowerCase().startsWith("find")) {
            parseFindCommand(task);
        } else if (task.equalsIgnoreCase("bye")) {
            parseByeCommand();
        } else {
            throw new DukeException(" Not a recognized Duke command!");
        }
        return command;
    }

    /**
     * Parses the list command
     * */
    private static void parseListCommand() {
        String userCommand;
        userCommand = "list";
        command = new Command(userCommand, notApplicable, notApplicable, notApplicable, notApplicable);
    }

    /**
     * Parses the mark command
     *
     * @param task The user Command Task
     * */
    private static void parseMarkCommand(String task) throws DukeException {
        String userCommand;
        try {
            userCommand = "mark";
            int taskToMarkIndex = 5;
            description = task.substring(taskToMarkIndex);
            command = new Command(userCommand, description, notApplicable, notApplicable, notApplicable);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" Task to mark is not recognized!");
        }
    }

    /**
     * Parses the unmark command
     *
     * @param task The user Command Task
     * */
    private static void parseUnmarkCommand(String task) throws DukeException {
        String userCommand;
        try {
            userCommand = "unmark";
            int taskToUnmarkIndex = 7;
            description = task.substring(taskToUnmarkIndex);
            command = new Command(userCommand, description, notApplicable, notApplicable, notApplicable);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" Task to unmark is not recognized!");
        }
    }

    /**
     * Parses the todo command
     *
     * @param task The user Command Task
     * */
    private static void parseTodoCommand(String task) throws DukeException {
        String userCommand;
        try {
            userCommand = "todo";
            int todoIndex = 5;
            description = task.substring(todoIndex);
            command = new Command(userCommand, description, notApplicable, notApplicable, notApplicable);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(" The todo description cannot be empty!");
        }
    }

    /**
     * Parses the deadline command
     *
     * @param task The user Command Task
     * */
    private static void parseDeadlineCommand(String task) throws DukeException {
        String userCommand;
        try {
            userCommand = "deadline";
            int descriptionIndex = 9;
            byIndex = task.indexOf("/by");
            description = task.substring(descriptionIndex, byIndex);
            int deadlineIndex = byIndex + 4;
            String deadline = task.substring(deadlineIndex);
            command = new Command(userCommand, description, deadline, notApplicable, notApplicable);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(" The entered deadline description is invalid!");
        }
    }

    /**
     * Parses the event command
     *
     * @param task The user Command Task
     * */
    private static void parseEventCommand(String task) throws DukeException {
        String userCommand;
        try {
            userCommand = "event";
            int descriptionIndex = 6;
            fromIndex = task.indexOf("/from");
            toIndex = task.indexOf("/to");
            description = task.substring(descriptionIndex, fromIndex);
            int eventStartIndex = fromIndex + 6;
            String eventStart = task.substring(eventStartIndex, toIndex);
            int eventEndIndex = toIndex + 4;
            String eventEnd = task.substring(eventEndIndex);
            command = new Command(userCommand, description, notApplicable, eventStart, eventEnd);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(" The entered event description is invalid!");
        }
    }

    /**
     * Parses the delete command
     *
     * @param task The user Command Task
     * */
    private static void parseDeleteCommand(String task) throws DukeException {
        String userCommand;
        try {
            userCommand = "delete";
            int taskToDeleteIndex = 7;
            description = task.substring(taskToDeleteIndex);
            command = new Command(userCommand, description, notApplicable, notApplicable, notApplicable);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" Task to delete is not recognized!");
        }
    }

    /**
     * Parses the find command
     *
     * @param task The user Command Task
     * */
    private static void parseFindCommand(String task) throws DukeException {
        String userCommand;
        try {
            userCommand = "find";
            int findIndex = 5;
            description = task.substring(findIndex);
            command = new Command(userCommand, description, notApplicable, notApplicable, notApplicable);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(" The find description cannot be empty!");
        }
    }

    /**
     * Parses the bye command
     * */
    private static void parseByeCommand() {
        String userCommand;
        userCommand = "bye";
        command = new Command(userCommand, notApplicable, notApplicable, notApplicable, notApplicable);
    }
}