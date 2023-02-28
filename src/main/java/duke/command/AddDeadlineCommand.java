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
    @ Override
    public void execute (TaskList tasks, Storage storage, UI ui){
        try {
            Task task = new Deadline(description, deadline);
            tasks.addTask(task);
            ui.printAddTask();
            ui.printTask(task);
            ui.printNoOfTasks(tasks.getSize());
            storage.saveData(tasks);

        } catch (IOException e) {
            System.out.println("Unable to save.");
        }

    }
}
