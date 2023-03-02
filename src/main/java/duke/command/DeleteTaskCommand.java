package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

public class DeleteTaskCommand extends Command {
    private int index;

    public DeleteTaskCommand (String line) {
            String[] words = line.split(" ");
            int itemNumber = Integer.parseInt(words[1]);
            index = itemNumber - 1;
    }

    /**
     * Deletes task at the specified position.
     *
     * @param taskList List to store all tasks.
     */
    @Override
    public void run(TaskList taskList) {
        Task item = taskList.lists.get(index);
        Ui.printRemoveTask(item);
        taskList.lists.remove(index);
        Ui.printListSize(taskList.lists.size());
    }
}
