package duke.functionalities;

import duke.exception.DukeException;

/**
 * This Class Deals with User Commands
 * */
public class Command {

    protected String command;
    protected String description;
    protected String eventStart;
    protected String eventEnd;
    protected String dueDate;

    /**
     * Reads in the User Command from Parser
     *
     * @param userCommand The type of user Command (list, mark, unmark, delete, find etc.)
     * @param userDescription The Task description
     * @param deadlineDate The Due Date for deadline type tasks
     * @param eventStart The Start Date/Time for event type tasks
     * @param eventEnd The End Date/Time for event type tasks
     * */
    public Command(String userCommand, String userDescription, String deadlineDate, String eventStart, String eventEnd) {
        this.command = userCommand;
        this.description = userDescription;
        this.dueDate = deadlineDate;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    /**
     * Executes the Duke Command with Exception Handling.
     *
     * @param tasks The Class that contains the Task List
     * @param ui The User Interface Class
     * @param storage The Duke File Storage Class
     * @throws DukeException if there is an error encountered when executing the Duke Command
     * */
    public void executeDukeCommands(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (command.equalsIgnoreCase("list")) {
            tasks.printAllTasks();
        } else if (command.equalsIgnoreCase("mark")) {
            tasks.markTaskAsComplete(description);
        } else if (command.equalsIgnoreCase("unmark")) {
            tasks.markTaskAsNotComplete(description);
        } else if (command.equalsIgnoreCase("todo")) {
            tasks.addTodo(description);
        } else if (command.equalsIgnoreCase("deadline")) {
            tasks.addDeadline(description, dueDate);
        } else if (command.equalsIgnoreCase("event")) {
            tasks.addEvent(description, eventStart, eventEnd);
        } else if (command.equalsIgnoreCase("delete")) {
            tasks.deleteTask(description);
        } else if (command.equalsIgnoreCase("find")) {
            tasks.findTask(description);
        } else if (command.equalsIgnoreCase("bye")) {
            ui.showUserMessage(" Bye. Hope to see you again soon!");
            storage.saveFile();
        } else {
            throw new DukeException(" Task entered is not a recognized Duke Command!");
        }
    }

    /**
     * Exits the Program when User enters "bye"
     *
     * @return Exit Status (True/False)
     * */
    public boolean isExit() {
        if (command == "bye") {
            return true;
        }
        return false;
    }
}