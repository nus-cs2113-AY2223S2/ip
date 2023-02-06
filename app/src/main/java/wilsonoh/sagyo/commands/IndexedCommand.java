package wilsonoh.sagyo.commands;

import wilsonoh.sagyo.exceptions.InvalidCommandException;
import wilsonoh.sagyo.tasklist.TaskList;

public interface IndexedCommand {

    default int getValidatedIndex(TaskList tasks, String idxGroup) throws InvalidCommandException {
        if (idxGroup == null) {
            throw new InvalidCommandException("This command must be followed by the index of a task");
        }
        int index = Integer.parseInt(idxGroup) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new InvalidCommandException(
                String.format("Task index %d is invalid for task list of size %d", index + 1, tasks.size()));
        }
        return index;
    }
}
