package Arsdorint.command;
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
    public static final String SYNTAX = "Syntax for date\n\t>>>date <yyyy-mm-dd>";
    public LocalDate date;

    /**
     * Execution of the "date" command
     *
     * @return printing the list of command happens on that date
     *
     */
    @Override
    public CommandRes execute() {
        ArrayList<Task> task = new ArrayList<>(TaskList.list.stream().filter(i -> !i.isDateNull()).filter
                (i -> i.getDate().equals(this.date)).collect(Collectors.toList()));
        long count = task.stream().count();
        String messageTop = count + " " + TaskList.printTasksOrTask((int) count)
                + printOccursOrOccur((int) count) + " on this date";
        return new CommandRes(messageTop, task, "");
    }
    private String printOccursOrOccur(int count) {
        return (count == 1) ? "occurs" : "occur";
    }
}


