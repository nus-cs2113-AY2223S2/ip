package duke.command;

/**
 * Enum representing the command type of the parsed command
 */
public enum CommandType {
    ADD_TODO_COMMAND,
    ADD_DEADLINE_COMMAND,
    ADD_EVENT_COMMAND,
    LIST_TASKS_COMMAND,
    MARK_TASK_COMMAND,
    UNMARK_TASK_COMMAND,
    END_PROGRAM_COMMAND,
    DELETE_TASK_COMMAND,
    FIND_TASK_COMMAND,
    UNKNOWN_COMMAND
}
