package Arsdorint.command;

import Arsdorint.MessageList;
import Arsdorint.data.TaskList;
import Arsdorint.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class CommandDate extends Command {
    public CommandDate(LocalDate date) {
        super(COMMAND_NAME);
        this.date = date;
    }
    public static final String COMMAND_NAME = "delete";
    public static final String SYNTAX = "TODO";
    public static final String MESSAGE_TOP = "TODO";
    public LocalDate date;

    @Override
    public CommandRes execute() {
        ArrayList<Task> task = new ArrayList<Task>(TaskList.list.stream().filter(i -> !i.isDateNull()).filter
                (i -> i.getDate().equals(this.date)).collect(Collectors.toList()));
        long count = task.stream().count();
        String messageTop = count + " tasks happen on this date";
        return new CommandRes(messageTop, task, "");
    }
}


