package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Deadline;
import duke.task.Task;

import java.io.IOException;

public class AddDeadlineCommand extends Command {

    private String description;
    private String deadline;
    public AddDeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    public void execute (TaskList tasks, Storage storage){

        try {

            Task task = new Deadline(description, deadline);
            tasks.addTask(task);
            UI.printAddTask();
            UI.printTask(task);
            UI.printNoOfTasks(tasks.getSize());

            storage.saveData(tasks);

        } catch (IOException e) {
            System.out.println("Unable to save.");
        }

    }
}
