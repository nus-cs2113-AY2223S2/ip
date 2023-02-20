package duke.command;

import duke.data.TaskData;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.filemanager.Storage;
import duke.task.Deadline;
import duke.task.Task;

import java.util.NoSuchElementException;

public class DeleteFromList extends Command {
    private int taskIndex;

    public DeleteFromList(String userInput) throws DukeException {
        try {
            String taskIndex = userInput.replace(" ", "");
            setDeleteTask(Integer.parseInt(taskIndex) - 1);
        } catch (NoSuchElementException | NumberFormatException ex) {
            throw new InvalidInputException();
        }
    }

    @Override
    public void executeCommand(TaskData taskData, Storage storage) throws DukeException {
        try {
            Task taskInfo = taskData.deleteTask(taskIndex, storage);
            System.out.println("Noted sir, I have removed \n"
                    + taskInfo.getTaskType() + "[ ]" + taskInfo.getDescription() + "\n"
                    + "from the list");
            System.out.println("Now you have " + taskData.size() + " task(s) in the list.");
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Enter a number within range of list!");
        }
    }

    public void setDeleteTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }
}
