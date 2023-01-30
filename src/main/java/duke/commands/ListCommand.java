package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import static duke.constants.Constants.LINEBREAK;

public class ListCommand extends Command{

    @Override
    public void handleCommand(String line, TaskList taskList){
        int indexCount = Task.getIndexCount();
        if (indexCount == 0) {
            DukeException.emptyList();
            return;
        }

        for (int i = 0; i < indexCount; i++) {
            System.out.println(i + 1 + ". " + taskList.getTask(i));
        }
        System.out.println(LINEBREAK);
    }
}
