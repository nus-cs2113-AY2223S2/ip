package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Task;


public class AddDeadlineCommand extends AddCommand {

    protected String[] stringSplit;

    public AddDeadlineCommand(String[] stringSplit) {
        this.stringSplit = stringSplit;
    }

    @Override
    public void execute(TaskList tasks, Storage database) {
        Task currTask = new Deadline(stringSplit[0], stringSplit[1]);
        addTaskToTaskList(tasks, currTask);
        addTaskToDatabase(currTask, database);
    }
}
