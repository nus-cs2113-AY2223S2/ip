package duke.exceptions;

import duke.tasks.Task;

public class TaskToMarkDoesNotExistException extends Exception {
    protected String command;

    public TaskToMarkDoesNotExistException(String command) {
        this.command = command;
    }

    public void printErrorMessage() {
        System.out.println("You can only " + command + " tasks that are currently in the list!");
    }
}
