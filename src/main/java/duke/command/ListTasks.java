package duke.command;

import duke.data.TaskData;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.task.Task;

import java.util.ArrayList;

public class ListTasks extends Command {

    @Override
    public void executeCommand(TaskData taskData, Storage storage) throws DukeException {
        System.out.println("Here are the tasks in your list:");
        if (taskData.size() == 0) {
            System.out.println("There are no tasks in your list!");
            return;
        }
        ArrayList<Task> readableList = taskData.getReadableList();
        int count = 1;
        for (Task i : readableList) {
            System.out.println(count + ". " + i.getTaskStatus());
            count++;
        }
        System.out.println("Now you have " + readableList.size() + " tasks in the list.");
    }
}
