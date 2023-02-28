package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;

public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;

    }

    public void execute(TaskList tasks, Storage storage, UI ui) {

        try {

            Task task = new Event(description, from, to);
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
