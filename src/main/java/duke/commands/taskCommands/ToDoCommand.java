package duke.commands.taskCommands;

import duke.commands.Command;
import duke.commands.DukeException;
import duke.commands.UI;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class ToDoCommand extends Command {

    @Override
    public void handleCommand(String line, TaskList taskList){
        String lineBreak = UI.getLineBreak();
        int markIndex;
        int indexCount = Task.getIndexCount();
        String description;
        try {
            markIndex = line.indexOf(' ');
            if (markIndex == -1) {
                DukeException.printError();
                System.out.println(lineBreak);
                return;
            }

            description = line.substring(markIndex + 1).trim();
            ToDo newTask = new ToDo(description);
            taskList.addTask(indexCount, newTask);
        } catch (Exception e) {
            DukeException.printError();
            System.out.println(lineBreak);
        }
    }
}
