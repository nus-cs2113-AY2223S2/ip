package inu.commands;

import inu.commons.Messages;
import inu.commons.Util;
import inu.task.TaskList;
import java.time.LocalDate;


public class ListWithDateCommand extends Command {

    public static final String COMMAND_WORD = "list";

    private final LocalDate date;

    public ListWithDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(Messages.MESSAGE_LIST_HEADER_WITH_DATE + Util.convertDateToString(date) + "\n"
                + taskList.printListByDate(date));
    }

}
