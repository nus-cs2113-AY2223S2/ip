package duke.commands.task;

import duke.commands.Command;
import duke.commands.CommandResult;

/**
 * Represents a task in the task list.
 */
public class Task extends Command {

    public String taskDescription;
    public String status;
    public String taskChar = "[ ]";

    public String formattedTask;


    public Task(String taskDescription, String taskStatus) {
        this.taskDescription = taskDescription;
        this.status = taskStatus;
    }

    /**
     * Changes the status of the task to a string representing a completed task
     */

    public void setDone() {
        this.status = "[X]";
        setFormattedTask();
    }

    /**
     * Changes the status of the task to a string representing an uncompleted task
     */

    public void setUndone() {
        this.status = "[ ]";
        setFormattedTask();
    }

    /**
     * Sets and updates any changes to the string representing the task when called
     */

    public void setFormattedTask() {
        formattedTask = taskChar + status + " " + taskDescription;
    }


    public void print() {
        System.out.println(formattedTask);
    }

    /**
     * Executes command and return results
     * Ensures that execute() is not implemented by Task class
     */

    public CommandResult execute() {
        throw new UnsupportedOperationException("method implemented by child classes");
    }
}
