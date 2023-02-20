package duke.command;

import duke.data.TaskData;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.filemanager.Storage;
import duke.task.Task;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(String userInput) throws DukeException {
        try {
            String taskIndex = userInput.replace(" ", "");
            setMarkTask(Integer.parseInt(taskIndex) - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            throw new InvalidInputException();
        }

    }

    @Override
    public void executeCommand(TaskData taskData, Storage storage) throws DukeException {
        try {
            Task taskInfo = taskData.markAsDone(taskIndex, storage);
            System.out.println("Noted sir, I have marked \n"
                    + taskInfo.getTaskType() + "[X] " + taskInfo.getDescription() + "\n"
                    + "as done.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number within list size!");
        }
    }

    public void setMarkTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }


}
