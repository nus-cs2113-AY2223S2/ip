package inu.commands;

import inu.commons.Messages;
import inu.commons.Util;
import inu.task.TaskList;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    public CommandResult execute(TaskList taskList) {
        return new CommandResult(Messages.MESSAGE_LIST_HEADER_WITH_KEYWORD + keyWord + "\n"
                + taskList.printList(taskList.filterKeyWord(keyWord)));
    }

}
