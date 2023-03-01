package command;

import exception.DukeException;
import exception.ErrorMessage;
import components.TaskList;
import components.Ui;
import components.Storage;


public class ToggleMarkCommand extends Command {
    public ToggleMarkCommand(String[] commandFields) {
        super(commandFields);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(commandFields[1]);

            if (taskNumber > tasks.tasks.size()) {
                throw new DukeException(ErrorMessage.INVALID_TASK.toString());
            }

            if (commandFields[0].equals("mark")) {
                tasks.tasks.get(taskNumber - 1).toggleMark(1);
                storage.writeToFile(tasks.tasks, storage.filePath);
                System.out.println("Nice! I've marked this task as done:");
            } else {
                tasks.tasks.get(taskNumber - 1).toggleMark(0);
                storage.writeToFile(tasks.tasks, storage.filePath);
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println(tasks.tasks.get(taskNumber - 1));
        } catch (NumberFormatException e) {
            throw new DukeException(ErrorMessage.INVALID_NUMBER.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
