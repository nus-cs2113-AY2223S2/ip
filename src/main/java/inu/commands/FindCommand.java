package inu.commands;

import inu.commons.Messages;
import inu.task.TaskList;

/**
 * Find a task by searching for a keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private final String keyWord;

    /**
     * Constructor.
     *
     * @param keyWord keyword to query.
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    public CommandResult execute(TaskList taskList) {
        return new CommandResult(Messages.MESSAGE_LIST_HEADER_WITH_KEYWORD + keyWord + "\n"
                + taskList.printList(taskList.filterKeyWord(keyWord)));
    }

}
