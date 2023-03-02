package io;

import task.*;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

/**
 * Utility class, no need to instantiate.
 * Use static methods to parse and process input.
 */
public class Parser {

    /**
     * Split input into 1st arg and subsequent line.
     * @param inputLine A line of input direct from user
     */
    public static String[] splitCommandAndArgs(String inputLine) {
        // Trim trailing and beginning whitespace, then split once. Output is [command, args].
        final String[] split = inputLine.trim().split("\\s+", 2);
        // Else case: no parameters
        return split.length == 2 ? split : new String[]{split[0], ""};
    }

    /**
     * Input validation for Todo Task. Check if input empty.
     * @param commandArgs Line input, should simply be the task name
     * @return the same input, unless blank
     * @throws DukeException only if blank
     */
    public static String processTaskTodo(String commandArgs) throws DukeException {
        if (commandArgs.isBlank()) {
            throw new DukeException();
        }
        return commandArgs;
    }

    /**
     * Input Validation for Deadline.
     */
    public static String[] processTaskDeadline(String commandArgs) throws DukeException {
        String[] deadlineArgs = commandArgs.split(Ui.COMMAND_TASK_DEADLINE_DELIMITER_REGEX);

        // Needs to be specifically 2, i.e. [task_name, deadline]
        // Is this a magic number?
        if (deadlineArgs.length != 2) {
            throw new DukeException();
        }
        return deadlineArgs;
    }

    /**
     * Input Validation for Event.
     */
    public static String[] processTaskEvent(String commandArgs) throws DukeException {
        // ensure it contains both.
        if (!commandArgs.contains(Ui.COMMAND_TASK_EVENT_DELIMITER1) ||
                !commandArgs.contains(Ui.COMMAND_TASK_EVENT_DELIMITER2)) {
            throw new DukeException();
        }

        // TODO check for inputs like /from abc /from abc /to
        String[] eventArgs = commandArgs.split(Ui.COMMAND_TASK_EVENT_DELIMITER_REGEX);

        // Needs to be specifically 3, i.e. [task_name, from, to]
        if (eventArgs.length != 3) {
            throw new DukeException();
        }
        return eventArgs;
    }

    /**
     * Credit to <a href="https://stackoverflow.com/questions/468789/is-there-a-way-in-java-to-determine-if-a-path-is-valid-without-attempting-to-cre">StackOverflow</a>
     * @param path path name for saving file
     * @return true if path is valid path
     */
    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException
                 | NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Performs each input's methods as long as it exists, if not return error
     * @param inputLine Input directly from command-line
     * @return Feedback string or error string
     */
    public static String executeCommand(TaskList tasks, Storage storage, String inputLine) {
        final String[] commandTypeAndArgs = Parser.splitCommandAndArgs(inputLine);
        final String command = commandTypeAndArgs[0];
        final String commandArgs = commandTypeAndArgs[1];

        // Check command against the set list of commands.
        // If it doesn't exist, default is invalid
        switch(command) {
        case Ui.COMMAND_HELP:
            return Ui.MESSAGE_HELP;
        case Ui.COMMAND_LIST:
            if (tasks.getNumberOfTasks() < 1) {
                return Ui.ERROR_TASKS_EMPTY;
            }
            return tasks.getTaskListString();
        case Ui.COMMAND_MARK: // Fallthrough
        case Ui.COMMAND_UNMARK:
            return tasks.executeMarkUnmark(command, commandArgs);
        case Ui.COMMAND_TASK_TODO:
            return handleAddTaskTodo(tasks, commandArgs);
        case Ui.COMMAND_TASK_DEADLINE:
            return handleAddTaskDeadline(tasks, commandArgs);
        case Ui.COMMAND_TASK_EVENT:
            return handleAddTaskEvent(tasks, commandArgs);
        case Ui.COMMAND_DELETE:
            return handleDelete(tasks, commandArgs);
        case Ui.COMMAND_FIND:
            return handleFind(tasks, commandArgs);
        case Ui.COMMAND_BYE:
            Ui.printExitMessage();
            tasks.writeAllToFile(storage);
            System.exit(0);
            // Fallthrough (If somehow cannot exit? LOL)
        default:
            return Ui.ERROR_MESSAGE_INVALID_COMMAND;
        }
    }

    private static String handleAddTaskTodo(TaskList tasks, String commandArgs) {
        try {
            Todo newTask = new Todo(Parser.processTaskTodo(commandArgs), tasks.getNextTaskNumber());
            tasks.addTask(newTask);
            return Ui.feedbackTaskAdded(tasks, newTask);
        } catch (DukeException e) {
            return Ui.ERROR_MESSAGE_ARGUMENT_MISSING;
        }
    }

    private static String handleAddTaskDeadline(TaskList tasks, String commandArgs) {
        try {
            String[] deadlineArgs = Parser.processTaskDeadline(commandArgs);
            Deadline newTask =
                    new Deadline(deadlineArgs[0], tasks.getNextTaskNumber(), deadlineArgs[1]);
            tasks.addTask(newTask);
            return Ui.feedbackTaskAdded(tasks, newTask);
        } catch (DukeException e) {
            return Ui.ERROR_MESSAGE_ARGUMENT_NUMBER;
        }
    }

    private static String handleAddTaskEvent(TaskList tasks, String commandArgs) {
        try {
            String[] eventArgs = Parser.processTaskEvent(commandArgs);
            Event newTask =
                    new Event(eventArgs[0], tasks.getNextTaskNumber(),
                            eventArgs[1], eventArgs[2]);
            tasks.addTask(newTask);
            return Ui.feedbackTaskAdded(tasks, newTask);
        } catch (DukeException e) {
            return Ui.ERROR_MESSAGE_ARGUMENT_NUMBER;
        }
    }

    private static String handleFind(TaskList tasks, String commandArgs) {
        return tasks.listAllMatchingKeyword(commandArgs);
    }

    /**
     * Validates input for delete command, and then deletes the task.
     * @param commandArgs 1-indexed number to be parsed as integer.
     * @return Feedback string, either successful delete or throw number exception.
     */
    private static String handleDelete(TaskList tasks, String commandArgs) {
        int taskNumber;

        // Parse Int first
        try {
            taskNumber = Integer.parseInt(commandArgs);
        } catch (NumberFormatException e) {
            return Ui.ERROR_MESSAGE_TASK_INDEX;
        }

        // Index out of bounds
        if (taskNumber > tasks.getNumberOfTasks()) {
            return Ui.ERROR_MESSAGE_TASK_INDEX;
        }

        Task deletedTask = tasks.deleteTask(taskNumber);
        String output = "Noted. I've removed this task:\n"
                + deletedTask.toString() + '\n'
                + "Now you have " + tasks.getNumberOfTasks() + " task(s) in the list.";
        return output;
    }


}
