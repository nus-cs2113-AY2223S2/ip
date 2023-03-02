package commands;

import data.TasksList;
import data.exceptions.SherlockException;
import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.HashMap;

public class AddCommand extends Command{
    String name;
    public AddCommand(String name) {
        this.name = name;
    }
    @Override
    public void execute(TasksList tasksList, Ui ui, Storage storage) throws SherlockException {

        tasksList.addTask(new Task(this.name, false));

        ui.printLines("added: " + this.name);

        storage.writeToFile(tasksList);
    }
}
