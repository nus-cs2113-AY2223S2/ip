package duke;

import duke.tasks.Task;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.TaskToMarkDoesNotExistException;
import duke.exceptions.UnknownCommandException;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Main class of Duke that contains code for Duke to function
 */
public class Duke {

    public static void main(String[] args) {
        Ui.printGreet();
        ArrayList<Task> tasks = TaskList.tasks;
        try {
            tasks = Storage.getData();
        } catch (IOException e) {
            System.out.println("Error obtaining data from file");
        }
        try {
            TaskList.editList();
        } catch (UnknownCommandException e) {
            e.printErrorMessage();
        } catch (EmptyDescriptionException e) {
            e.printErrorMessage();
        } catch (TaskToMarkDoesNotExistException e) {
            e.printErrorMessage();
            Ui.printNumberOfTasks(tasks);
        }
    }
}