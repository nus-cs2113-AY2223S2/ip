package duke.commands.task;

import duke.commands.Command;
import duke.commands.CommandResult;

import java.util.ArrayList;

public class Task extends Command {

    public String taskDescription;
    public String status;
    public String taskChar = "[ ]";

    public String formattedTask;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.status = "[ ]";
    }

    public void setDone() {
        status = "[X]";
        setFormattedTask();
    }

    public void setUndone() {
        status = "[ ]";
        setFormattedTask();
    }

    public void setFormattedTask() {
        formattedTask = taskChar + status + " " + taskDescription;
    }


    public void print() {
        System.out.println(formattedTask);
    }


    public CommandResult execute() {
        throw new UnsupportedOperationException("method implemented by child classes");
    }
}
