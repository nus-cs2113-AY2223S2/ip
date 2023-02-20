package duke.command;

import duke.data.TaskData;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.filemanager.Storage;
import duke.task.Task;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class AddTodoToList extends Command {
    private Task newTask;

    /**
     * create a new todo object
     *
     * @param userInput data containing todo description
     * @throws DukeException occurs when an invalid input is found
     *                       i.e missing description
     */
    public AddTodoToList(String userInput) throws DukeException {
        try {
            this.newTask = new Task(userInput);
        } catch (IndexOutOfBoundsException | NoSuchElementException ex) {
            throw new InvalidInputException();
        }
    }

    /**
     * Adds new todo object into the arrayList
     *
     * @param taskData containing all tasks
     */
    @Override
    public void executeCommand(TaskData taskData, Storage storage) throws DukeException {
        taskData.add(newTask, storage);
        System.out.println("Got it! Added \n"
                + "[T][ ]" + newTask.getDescription() + "\n"
                + "to the list.");
        System.out.println("Now you have " + taskData.size() + " task(s) in the list.");
    }

}
