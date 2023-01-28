package duke.commands;

import duke.main.Duke;
import duke.task.Task;
import duke.task.TaskList;

public class ListCommand extends Command{

    @Override
    public void handleCommand(String line, TaskList taskList){
        int indexCount = Task.getIndexCount();
        String lineBreak = UI.getLineBreak();
        if (indexCount == 0) {
            DukeException.emptyList();
            return;
        }

        for (int i = 0; i < indexCount; i++) {
            System.out.println(i + 1 + ". " + taskList.getTask(i));
        }
        System.out.println(lineBreak);
    }
}
