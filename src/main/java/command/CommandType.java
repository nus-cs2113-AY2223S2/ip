package command;

/**
 *  Commands that can be used.
 *  <p></p>
 *  {@link #LIST}
 *  {@link #TODO}
 *  {@link #DEADLINE}
 *  {@link #EVENT}
 *  {@link #MARK}
 *  {@link #UNMARK}
 *  {@link #FIND}
 *  {@link #DELETE}
 *  {@link #HELP}
 *  {@link #EXIT}
 *  {@link #UNKNOWN}
 */
public enum CommandType {
    /**
     * Lists out the current task list.
     */
    LIST,
    /**
     * Adds a todo task to the task list.
     */
    TODO,
    /**
     * Adds a deadline task to the task list.
     */
    DEADLINE,
    /**
     * Adds an event task the task list.
     */
    EVENT,
    /**
     * Marks a particular task on the task list as done.
     */
    MARK,
    /**
     * Marks a particular task on the task list as not done.
     */
    UNMARK,
    /**
     * Finds all tasks containing keywords on the current task list.
     */
    FIND,
    /**
     * Removes a particular task from the task list.
     */
    DELETE,
    /**
     * Shows the full list of commands.
     */
    HELP,
    /**
     * Terminates the programme and exit with saving.
     */
    EXIT,
    /**
     * Command not recognized.
     */
    UNKNOWN
}
