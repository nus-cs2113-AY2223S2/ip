package duke.command;

import duke.ui.Ui;

public class ListCommand {
    public static final String COMMAND_WORD = "list";
    public static final String EMPTY_MESSAGE = " The list is empty!";
    public static final String MESSAGE = " Here are the tasks in your lists:";
    public static final String MESSAGE_USAGE = " list" + ": view all tasks in the task list. "
            + Ui.NEW_LINE + "  Example: list";
}
