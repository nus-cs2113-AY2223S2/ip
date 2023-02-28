package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;

public class AddTodoCommand extends Command {

    private String description;
    public AddTodoCommand(String description) {
        this.description = description;

    }
    public void execute(TaskList tasks, Storage storage, UI ui) {

        try {

            ui.printAddTask();

            Task task = new Todo(description);
            tasks.addTask(task);

            ui.printTask(task);
            ui.printNoOfTasks(tasks.getSize());
            storage.saveData(tasks);

        }   catch (IOException e) {
            System.out.println("Unable to save.");
        }
    }



}
