package duke.functionalities;

import duke.exception.DukeException;

public class Command {

    protected String command;
    protected String description;
    protected String eventStart;
    protected String eventEnd;
    protected String dueDate;

    public Command(String userCommand, String userDescription, String deadlineDate, String eventStartDate, String eventEndDate) {
        this.command = userCommand;
        this.description = userDescription;
        this.dueDate = deadlineDate;
        this.eventStart = eventStartDate;
        this.eventEnd = eventEndDate;
    }

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

    public boolean isExit() {
        if (command == "bye") {
            return true;
        }
        return false;
    }
}