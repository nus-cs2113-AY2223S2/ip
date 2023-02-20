package duke.command;

import duke.data.TaskData;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.filemanager.Storage;
import duke.task.Task;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(String userInput) throws DukeException {
        try {
            String taskIndex = userInput.replace(" ", "");
            setUnmarkTask(Integer.parseInt(taskIndex) - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            throw new InvalidInputException();
        }
    }

    @Override
    public void executeCommand(TaskData taskData, Storage storage) throws DukeException {
        try {
            Task taskInfo = taskData.markAsUndone(taskIndex, storage);
            System.out.println("Noted sir, I have marked \n"
                    + taskInfo.getTaskType() + "[ ]" + taskInfo.getDescription() + "\n"
                    + "as not done.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number within list size!");
        }
    }

    public void setUnmarkTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }


}
