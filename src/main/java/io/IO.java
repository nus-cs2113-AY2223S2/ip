package io;

/**
 * This class manages Input and Output for Duke.<br>
 * Includes Input validation, processing arguments, and also file writing I/O.<br>
 * Credits to Contacts in Week 4 for input processing/handling methods.
 * @author Choong Zhan Hong
 */
public final class IO {

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

    public static String[] processTaskDeadline(String commandArgs) throws DukeException {
        String[] deadlineArgs = commandArgs.split(Ui.COMMAND_TASK_DEADLINE_DELIMITER_REGEX);

        // Needs to be specifically 2, i.e. [task_name, deadline]
        // Is this a magic number?
        if (deadlineArgs.length != 2) {
            throw new DukeException();
        }
        return deadlineArgs;
    }

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

}
