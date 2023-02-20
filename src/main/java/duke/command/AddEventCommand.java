package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Event;
import duke.task.Task;



public class AddEventCommand extends AddCommand {

    protected String description;
    protected String from;

    protected String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Storage database) {
        Task currTask = new Event(description, from, to);
        addTaskToTaskList(tasks, currTask);
        addTaskToDatabase(currTask, database);
    }
}
